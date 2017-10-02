package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.table.SectionDataType
import java.nio.ByteBuffer

import com.tsunderebug.scolor.Font
import spire.math.{UByte, UInt, UShort}

case class UInt16(value: UShort) extends SectionDataType {

  override def getBytes(f: Font): Array[UByte] = {
    val buffer = ByteBuffer.allocate(2)
    buffer.putShort(value.toShort)
    buffer.array().map(UByte(_))
  }

  override def length: UInt = UInt(2)

  /**
    * Gets data sections if this data block has offsets
    *
    * @param f The font
    * @return an array of Data objects
    */
  override def data(f: Font) = Array()
}