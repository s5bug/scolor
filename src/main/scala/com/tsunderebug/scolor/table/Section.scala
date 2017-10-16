package com.tsunderebug.scolor.table

import com.tsunderebug.scolor.ByteAllocator
import spire.math.UByte

case class Section(name: String, data: SectionDataType) {

  def getBytes(b: ByteAllocator): Array[UByte] = data.getBytes(b)

}
