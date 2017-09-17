package com.tsunderebug.scolor.table

import com.tsunderebug.scolor.Font
import spire.math.UByte

trait SectionDataType {

  def getBytes(f: Font): Array[UByte]

}
