package com.tsunderebug.scolor.otf

import java.io.{File, FileOutputStream}
import java.nio.{ByteBuffer, ByteOrder}

import com.tsunderebug.scolor._
import com.tsunderebug.scolor.otf.types.Offset32
import com.tsunderebug.scolor.table.Table
import spire.math.{UByte, UInt, UShort}
import spire.syntax.std.array._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

case class OpenTypeFont(tables: Seq[Table]) extends Font {

  private var nextAvailableOffset: Offset = Offset32(0)
  private val byteMap: mutable.Map[Offset, UByte] = mutable.Map()
  private val allocMap: mutable.Map[Data, Offset] = mutable.Map()
  private val tableOffsMap: mutable.Map[Table, Offset] = mutable.Map()

  writeHeader()
  writeData()

  private def writeHeader(): Unit = {
    def mPow2Shifts(i: Int): Int = {
      var shifts = 0
      while ((i & (Int.MaxValue << shifts)) != 0) {
        shifts = shifts + 1
      }
      shifts
    }

    def mPow2Less(i: Int): Int = {
      1 << (mPow2Shifts(i) - 1)
    }

    allocate(UInt(12))
    val buff = ListBuffer[UByte]()
    buff ++= "OTTO".getBytes.map(UByte(_))
    buff ++= UShort(tables.size).bytes
    val searchRange = mPow2Less(tables.size) * 16
    buff ++= UShort(searchRange).bytes
    buff ++= UShort(mPow2Shifts(tables.size)).bytes
    buff ++= UShort(tables.size * 16 - searchRange).bytes
    allocate(UInt(tables.length * 16))
    tables.foreach((t) => {
      buff ++= t.name.getBytes.map(UByte(_))
      val dataOffset = allocate(t)
      tableOffsMap += (t -> dataOffset)
      val bytes = t.getBytes(this)
      buff ++= bytes.checksum.bytes
      buff ++= dataOffset.position.bytes
      buff ++= t.length.bytes
    })
    insert(Offset32(0), buff.toArray)
  }

  private def writeData(): Unit = {
    tableOffsMap.foreach((t) => {
      insert(t._1)
    })
  }

  override protected[scolor] def allocate(data: Data): Offset = {
    allocMap.getOrElseUpdate(data, allocate(data.length))
  }

  override protected[scolor] def allocate(numBytes: UInt): Offset = {
    val prev = nextOffset
    nextAvailableOffset = Offset32((prev.position + numBytes).toLong)
    prev
  }

  override protected[scolor] def insert(offset: Offset, data: Data): Unit = {
    val bytes = data.getBytes(this)
    val top = offset.position.toInt + bytes.length
    (offset.position.toInt until top).foreach((p) => byteMap += (Offset32(p) -> bytes(p - offset.position.toInt)))
    nextAvailableOffset = Offset32(top + (4 - (top % 4)))
    data.getData(this).foreach(insert)
  }

  override def getBytes: Array[UByte] = {
    val m: Int = byteMap.map((t) => t._1.position.toInt).max
    (0 to m).map((i) => byteMap.getOrElse(Offset32(i), UByte(0))).toArray
  }

  override def writeFile(dir: File, name: String): Unit = {
    dir.mkdirs()
    val `Windows Font Tables` = tables.filter((t) => !Seq("sbix", "cbdt", "cblc").contains(t.name.toLowerCase))
    val `Mac Font Tables` = tables.filter((t) => !Seq("colr", "cpal", "cbdt", "cblc").contains(t.name.toLowerCase))
    val `Linux Font Tables` = tables.filter((t) => !Seq("sbix", "colr", "cpal").contains(t.name.toLowerCase))
    val winFont = OpenTypeFont(`Windows Font Tables`)
    val macFont = OpenTypeFont(`Mac Font Tables`)
    val nixFont = OpenTypeFont(`Linux Font Tables`)
    val allFile = new File(dir, name + ".otf")
    val winFile = new File(dir, name + "-win.otf")
    val macFile = new File(dir, name + "-mac.otf")
    val nixFile = new File(dir, name + "-nix.otf")
    allFile.createNewFile()
    winFile.createNewFile()
    macFile.createNewFile()
    nixFile.createNewFile()
    val allOS: FileOutputStream = new FileOutputStream(allFile, false)
    val winOS: FileOutputStream = new FileOutputStream(winFile, false)
    val macOS: FileOutputStream = new FileOutputStream(macFile, false)
    val nixOS: FileOutputStream = new FileOutputStream(nixFile, false)
    allOS.write(getBytes.map(_.toByte))
    winOS.write(winFont.getBytes.map(_.toByte))
    macOS.write(macFont.getBytes.map(_.toByte))
    nixOS.write(nixFont.getBytes.map(_.toByte))
    allOS.close()
    winOS.close()
    macOS.close()
    nixOS.close()
  }

  override protected[scolor] def nextOffset: Offset = nextAvailableOffset

  implicit class OpenTypeTable(bytes: Array[UByte]) {

    def checksum: UInt = {
      val intBuf = ByteBuffer.wrap(bytes.map(_.signed)).order(ByteOrder.BIG_ENDIAN).asIntBuffer
      val ints = (bytes.length + 3) / 4
      (0 until ints).map((i) => UInt(intBuf.get(i))).toArray.qsum
    }

  }

  implicit class BytableUShort(s: UShort) {

    def bytes: Array[UByte] = {
      Array(((s.toShort & 0xFF00) >> 8).toByte, (s.toShort & 0x00FF).toByte).map(UByte(_))
    }

  }

  implicit class BytableUInt(i: UInt) {

    def bytes: Array[UByte] = {
      Array(((i.toInt & 0xFF000000) >> 24).toByte, ((i.toInt & 0x00FF0000) >> 16).toByte, ((i.toInt & 0x0000FF00) >> 8).toByte, (i.toInt & 0x000000FF).toByte).map(UByte(_))
    }

  }

  implicit def byteArrayToData(b: Array[UByte]): Data = new Data {

    /**
      * Gets data sections if this data block has offsets
      *
      * @param f The font
      * @return an array of Data objects
      */
    override def getData(f: Font): Array[Data] = Array()

    override def length: UInt = UInt(b.length)

    override def getBytes(f: Font): Array[UByte] = b

  }

}
