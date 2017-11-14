package com.tsunderebug.scolor.otf.types.num

import com.tsunderebug.scolor.ByteAllocator
import com.tsunderebug.scolor.table.SectionDataType
import spire.math.{UByte, UInt}

case class OTFInt8(value: Byte) extends SectionDataType {

  /**
    * @param b The byte allocator
    * @return an array of unsigned bytes representing the data.
    */
  override def bytes(b: ByteAllocator): Array[UByte] = Array(value).map(UByte(_))

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator) = UInt(1)

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator) = Seq()

}
