package com.tsunderebug.scolor.otf

import java.io.{File, FileOutputStream}
import java.nio.{ByteBuffer, ByteOrder}

import com.tsunderebug.scolor._
import com.tsunderebug.scolor.otf.tables.OTFHEADTable
import com.tsunderebug.scolor.otf.types.OTFOffset32
import com.tsunderebug.scolor.table.Table
import spire.math.{UByte, UInt, UShort}
import spire.syntax.std.array._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class OpenTypeFont(tables: Traversable[Table]) extends Font {

  private val tableOffsMap: mutable.Map[Table, Offset] = mutable.Map()

  implicit def byteArrayToData(ba: Array[UByte]): Data = new Data {

    /**
      * Gets data sections if this data block has offsets
      *
      * @param b The byte allocator
      * @return an array of Data objects
      */
    override def data(b: ByteAllocator): Traversable[Data] = Seq()

    override def length(b: ByteAllocator): UInt = UInt(ba.length)

    override def bytes(b: ByteAllocator): Array[UByte] = ba

  }

  def writeTables(b: ByteAllocator): Unit = {
    tableOffsMap.foreach((t) => {
      b.insert(t._1)
    })
  }

  def writeHeader(b: ByteAllocator): Unit = {
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

    b.allocate(UInt(12))
    val buff = ListBuffer[UByte]()
    buff ++= "OTTO".getBytes.map(UByte(_))
    buff ++= UShort(tables.size).bytes
    val searchRange = mPow2Less(tables.size) * 16
    buff ++= UShort(searchRange).bytes
    buff ++= UShort(mPow2Shifts(tables.size)).bytes
    buff ++= UShort(tables.size * 16 - searchRange).bytes
    b.allocate(UInt(tables.size * 16))
    tables.foreach((t) => {
      buff ++= t.name.getBytes.map(UByte(_))
      val dataOffset = b.allocate(t)
      tableOffsMap += (t -> dataOffset)
      val bytes = t.bytes(b)
      buff ++= bytes.checksum.bytes
      buff ++= dataOffset.position.bytes
      buff ++= t.length(b).bytes
    })
    b.insert(OTFOffset32(0), buff.toArray)
  }

  def writeHeaderSecondPass(b: ByteAllocator, newHead: OTFHEADTable): Unit = {
    tableOffsMap.clear()

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

    b.allocate(UInt(12))
    val buff = ListBuffer[UByte]()
    buff ++= "OTTO".getBytes.map(UByte(_))
    buff ++= UShort(tables.size).bytes
    val searchRange = mPow2Less(tables.size) * 16
    buff ++= UShort(searchRange).bytes
    buff ++= UShort(mPow2Shifts(tables.size)).bytes
    buff ++= UShort(tables.size * 16 - searchRange).bytes
    b.allocate(UInt(tables.size * 16))
    tables.foreach((t) => {
      if (t.name == "head") {
        buff ++= newHead.name.getBytes.map(UByte(_))
        val dataOffset = b.allocate(newHead)
        tableOffsMap += (newHead -> dataOffset)
        buff ++= t.bytes(b).checksum.bytes
        buff ++= dataOffset.position.bytes
        buff ++= newHead.length(b).bytes
      } else {
        buff ++= t.name.getBytes.map(UByte(_))
        val dataOffset = b.allocate(t)
        tableOffsMap += (t -> dataOffset)
        val bytes = t.bytes(b)
        buff ++= bytes.checksum.bytes
        buff ++= dataOffset.position.bytes
        buff ++= t.length(b).bytes
      }
    })
    b.insert(OTFOffset32(0), buff.toArray)
  }

  def writeTablesSecondPass(b: ByteAllocator, newHead: OTFHEADTable): Unit = {
    tableOffsMap.foreach((t) => {
      if (t._1.name == "head") {
        b.insert(newHead)
      } else {
        b.insert(t._1)
      }
    })
  }

  override def writeFile(dir: File, name: String): Unit = {
    dir.mkdirs()
    val `Windows Font Tables` = tables.filter((t) => !Seq("cbdt", "cblc").contains(t.name.toLowerCase))
    val `Mac Font Tables` = tables.filter((t) => !Seq("colr", "cpal", "cbdt", "cblc").contains(t.name.toLowerCase))
    val `Linux Font Tables` = tables.filter((t) => !Seq("sbix", "colr", "cpal").contains(t.name.toLowerCase))
    val winFont = new OpenTypeFont(`Windows Font Tables`)
    val macFont = new OpenTypeFont(`Mac Font Tables`)
    val nixFont = new OpenTypeFont(`Linux Font Tables`)
    safeCreateAndWrite(new File(dir, name + ".otf"), getBytes)
    safeCreateAndWrite(new File(dir, name + "-win.otf"), winFont.getBytes)
    safeCreateAndWrite(new File(dir, name + "-mac.otf"), macFont.getBytes)
    safeCreateAndWrite(new File(dir, name + "-nix.otf"), nixFont.getBytes)
  }

  implicit class OpenTypeTable(bytes: Array[UByte]) {

    def checksum: UInt = {
      val intBuf = ByteBuffer.wrap(bytes.map(_.signed)).order(ByteOrder.BIG_ENDIAN).asIntBuffer
      val ints = (bytes.length + 3) / 4 - 1
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

  /** Writes bytes to a file without leaving resources open on failure */
  private def safeCreateAndWrite(file: File, uBytes: => Array[UByte]): Unit = {
    file.createNewFile()
    val fileOutputStream = new FileOutputStream(file, false)
    try {
      fileOutputStream.write(uBytes.map(_.toByte))
    } finally {
      fileOutputStream.close()
    }
  }

  override def getBytes: Array[UByte] = {
    val b: ByteAllocator = new OTFByteAllocator(this)
    writeHeader(b)
    writeTables(b)
    tables.find(_.name == "head") match {
      case Some(t) =>
        val nb: ByteAllocator = new OTFByteAllocator(this)
        val newHead = t.asInstanceOf[OTFHEADTable].copy(checkSumAdjustment = UInt(0xB1B0AFBA - b.getBytes.checksum.signed))
        writeHeaderSecondPass(nb, newHead)
        writeTablesSecondPass(b, newHead)
        nb.getBytes
      case None => b.getBytes
    }
  }

}
