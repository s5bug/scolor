package com.tsunderebug.scolor.table

import com.tsunderebug.scolor.{Data, Font}
import spire.math.UByte

trait SectionDataType extends Data {

  def getBytes(f: Font): Array[UByte]

}
