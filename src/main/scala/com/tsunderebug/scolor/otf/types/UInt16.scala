package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.table.SectionDataType
import java.nio.ByteBuffer

import com.tsunderebug.scolor.Font
import spire.math.{UByte, UShort}

case class UInt16(value: UShort) extends SectionDataType {

  override def getBytes(f: Font): Array[UByte] = {
    val buffer = ByteBuffer.allocate(2)
    buffer.putShort(value.toShort)
    buffer.array().map(UByte(_))
  }

}