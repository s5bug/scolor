package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.otf.types.num.OTFUInt32
import com.tsunderebug.scolor.table.SectionDataType
import com.tsunderebug.scolor.{ByteAllocator, Data}
import spire.math.{UByte, UInt}

case class OTFFixedPoint(float: Double) extends SectionDataType {

  private val internal = OTFUInt32(UInt((float * (1 << 16)).toInt))

  /**
    * @param b The byte allocator
    * @return an array of unsigned bytes representing the data.
    */
  override def bytes(b: ByteAllocator): Array[UByte] = internal.bytes(b)

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator): UInt = UInt(4)

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator): Traversable[Data] = Seq()

}
