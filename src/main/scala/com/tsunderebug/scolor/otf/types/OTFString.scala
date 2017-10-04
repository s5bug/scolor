package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.Font
import com.tsunderebug.scolor.table.SectionDataType
import spire.math.{UByte, UInt}

case class OTFString(s: String) extends SectionDataType {

  override def getBytes(f: Font): Array[UByte] = s.getBytes.map(UByte(_))

  override def length(f: Font): UInt = UInt(s.length)

  /**
    * Gets data sections if this data block has offsets
    *
    * @param f The font
    * @return an array of Data objects
    */
  override def getData(f: Font) = Array()

}
