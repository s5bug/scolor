package com.tsunderebug.scolor.otf.types.num

import com.tsunderebug.scolor.table.SectionDataType
import com.tsunderebug.scolor.{ByteAllocator, Data}
import spire.math.{UByte, UInt}

case class OTFInt64(value: Long) extends SectionDataType {

  /**
    * @param b The byte allocator
    * @return an array of unsigned bytes representing the data.
    */
  override def bytes(b: ByteAllocator): Array[UByte] = {
    Array(((value & 0xFF00000000000000l) >> 64).toByte, ((value & 0x00FF000000000000l) >> 48).toByte, ((value & 0x0000FF0000000000l) >> 40).toByte, ((value & 0x000000FF00000000l) >> 32).toByte, ((value & 0x00000000FF000000l) >> 24).toByte, ((value & 0x0000000000FF0000l) >> 16).toByte, ((value & 0x000000000000FF00l) >> 8).toByte, (value & 0x00000000000000FFl).toByte).map(UByte(_))
  }

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator): UInt = UInt(8)

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator): Traversable[Data] = Seq()

}
