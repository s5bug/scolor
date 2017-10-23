package com.tsunderebug.scolor.table

import com.tsunderebug.scolor.ByteAllocator
import spire.math.UByte

case class Section(name: String, data: SectionDataType) {

  /**
    * @param b The byte allocator
    * @return the bytes of data
    */
  def getBytes(b: ByteAllocator): Array[UByte] = data.bytes(b)

}
