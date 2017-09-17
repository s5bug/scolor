package com.tsunderebug.scolor.table

import com.tsunderebug.scolor.Font
import spire.math.UByte

abstract class EnclosingSectionDataType extends SectionDataType {

  def sections(f: Font): Seq[Section]

  override def getBytes(f: Font): Array[UByte] = sections(f).flatMap(_.getBytes(f)).toArray

}
