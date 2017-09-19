package com.tsunderebug.scolor.otf

import java.io.File
import java.nio.{ByteBuffer, ByteOrder}

import com.tsunderebug.scolor._
import com.tsunderebug.scolor.table.Table
import spire.math.{UByte, UInt}

class OpenTypeFont(tables: Seq[Table]) extends Font {

  override def getBytes: Array[Byte] = ???

  override def writeFile(dir: File, name: String): Unit = ???

  override def nextAvailableOffset(d: Data): Offset = ???

  implicit class OpenTypeTable(bytes: Array[UByte]) {

    def checksum: UInt = {
      val intBuf = ByteBuffer.wrap(bytes.map(_.signed)).order(ByteOrder.BIG_ENDIAN).asIntBuffer
      intBuf.array.map(UInt(_)).sum(new Numeric[UInt] {
        override def plus(x: UInt, y: UInt): UInt = x + y
        override def minus(x: UInt, y: UInt): UInt = x - y
        override def times(x: UInt, y: UInt): UInt = x * y
        override def negate(x: UInt): UInt = -x
        override def fromInt(x: Int) = UInt(x)
        override def toInt(x: UInt): Int = x.toInt
        override def toLong(x: UInt): Long = x.toLong
        override def toFloat(x: UInt): Float = x.toFloat
        override def toDouble(x: UInt): Double = x.toDouble
        override def compare(x: UInt, y: UInt): Int = (y - x).toInt
      })
    }

  }
}
