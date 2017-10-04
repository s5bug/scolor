package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.Font
import com.tsunderebug.scolor.table.SectionDataType
import spire.math.{UByte, UInt}

case class UInt32(value: UInt) extends SectionDataType {

  override def getBytes(f: Font): Array[UByte] = {
    Array(((value.toInt & 0xFF000000) >> 24).toByte, ((value.toInt & 0x00FF0000) >> 16).toByte, ((value.toInt & 0x0000FF00) >> 8).toByte, (value.toInt & 0x000000FF).toByte).map(UByte(_))
  }

  override def length(f: Font) = UInt(4)

  /**
    * Gets data sections if this data block has offsets
    *
    * @param f The font
    * @return an array of Data objects
    */
  override def getData(f: Font) = Array()

}