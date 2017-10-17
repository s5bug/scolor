package com.tsunderebug.scolor.table

import com.tsunderebug.scolor.{ByteAllocator, Data}
import spire.math.UByte

trait SectionDataType extends Data {

  /**
    * @param b The byte allocator
    * @return an array of unsigned bytes representing the data.
    */
  def getBytes(b: ByteAllocator): Array[UByte]

}
