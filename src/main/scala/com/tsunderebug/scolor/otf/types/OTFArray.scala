package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.table.SectionDataType
import com.tsunderebug.scolor.{ByteAllocator, Data}
import spire.math.{UByte, UInt}

case class OTFArray[T <: Data](elems: Traversable[T]) extends SectionDataType {

  override def length(b: ByteAllocator): UInt = {
    elems.foldLeft(UInt(0)) {
      case (accum, elem) => accum + elem.length(b)
    }
  }

  override def bytes(b: ByteAllocator): Array[UByte] = {
    elems.foldLeft(Array.empty[UByte]) {
      case (accum, elem) => accum ++ elem.bytes(b)
    }
  }

  /**
    * Gets data sections if this data block has offsets
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator): Traversable[Data] = Seq()

}
