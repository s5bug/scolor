package com.tsunderebug.scolor.otf

import java.io.{File, FileOutputStream}
import java.nio.{ByteBuffer, ByteOrder}

import com.tsunderebug.scolor._
import com.tsunderebug.scolor.otf.types.Offset32
import com.tsunderebug.scolor.table.Table
import spire.math.{UByte, UInt}
import spire.syntax.std.array._

import scala.collection.mutable

case class OpenTypeFont(tables: Seq[Table]) extends Font {

  private var nextAvailableOffset: Offset = Offset32(0)
  private val byteMap: mutable.Map[Offset, UByte] = mutable.Map()

  private def insert(bytes: Array[UByte]): Unit = {
    val top = nextAvailableOffset.position.toLong + bytes.length
    (nextAvailableOffset.position.toLong until top).foreach((p) => byteMap += (Offset32(p) -> bytes(p)))
    nextAvailableOffset = Offset32(top + (4 - (top % 4)))
  }

  override def getBytes: Array[UByte] = {
    val m: Long = byteMap.map((t) => t._1.position.toLong).max
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

  override def nextAvailableOffset(d: Data): Offset = nextAvailableOffset

  implicit class OpenTypeTable(bytes: Array[UByte]) {

    def checksum: UInt = {
      val intBuf = ByteBuffer.wrap(bytes.map(_.signed)).order(ByteOrder.BIG_ENDIAN).asIntBuffer
      intBuf.array.map(UInt(_)).qsum
    }

  }
}
