package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.table.SectionDataType
import com.tsunderebug.scolor.{ByteAllocator, Data}
import spire.math.{UByte, UInt}
import spire.syntax.std.array._

case class OTFArray[T <: Data](elems: Seq[T]) extends SectionDataType {

  override def length(b: ByteAllocator): UInt = elems.map(_.length(b)).toArray.qsum

  override def getBytes(b: ByteAllocator): Array[UByte] = elems.flatMap(_.getBytes(b)).toArray

  /**
    * Gets data sections if this data block has offsets
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def getData(b: ByteAllocator): Seq[Data] = Seq()

}
