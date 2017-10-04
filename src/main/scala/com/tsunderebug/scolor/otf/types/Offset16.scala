package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.{Font, Offset}
import spire.math.{UByte, UInt, UShort}

case class Offset16(offset: Int) extends Offset {

  override def position: UInt = UInt(offset)

  override def getBytes(f: Font): Array[UByte] = UInt16(UShort(offset)).getBytes(f)

  override def length(f: Font) = UInt(2)

  /**
    * Gets data sections if this data block has offsets
    *
    * @param f The font
    * @return an array of Data objects
    */
  override def getData(f: Font) = Array()

}
