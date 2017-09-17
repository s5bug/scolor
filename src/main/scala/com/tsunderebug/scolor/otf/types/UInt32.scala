package com.tsunderebug.scolor.otf.types

import java.nio.ByteBuffer

import com.tsunderebug.scolor.Font
import com.tsunderebug.scolor.table.SectionDataType
import spire.math.{UByte, UInt}

case class UInt32(value: UInt) extends SectionDataType {

  override def getBytes(f: Font): Array[UByte] = {
    val buffer = ByteBuffer.allocate(4)
    buffer.putInt(value.toInt)
    buffer.array().map(UByte(_))
  }

}