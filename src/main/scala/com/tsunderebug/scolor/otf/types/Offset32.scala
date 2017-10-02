package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.{Font, Offset}
import spire.math.{UByte, UInt}

case class Offset32(offset: Long) extends Offset {

  override def position: UInt = UInt(offset)

  override def getBytes(f: Font): Array[UByte] = UInt32(UInt(offset)).getBytes(f)

  override def length = UInt(4)

  /**
    * Gets data sections if this data block has offsets
    *
    * @param f The font
    * @return an array of Data objects
    */
  override def data(f: Font) = Array()

}
