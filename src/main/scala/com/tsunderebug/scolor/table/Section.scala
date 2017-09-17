package com.tsunderebug.scolor.table

import com.tsunderebug.scolor.Font
import spire.math.UByte

case class Section(name: String, data: SectionDataType) {

  def getBytes(f: Font): Array[UByte] = data.getBytes(f)

}
