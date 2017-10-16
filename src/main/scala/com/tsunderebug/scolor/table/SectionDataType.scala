package com.tsunderebug.scolor.table

import com.tsunderebug.scolor.{ByteAllocator, Data}
import spire.math.UByte

trait SectionDataType extends Data {

  def getBytes(b: ByteAllocator): Array[UByte]

}
