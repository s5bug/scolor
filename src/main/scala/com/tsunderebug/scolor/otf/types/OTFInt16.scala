package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.ByteAllocator
import com.tsunderebug.scolor.table.SectionDataType
import spire.math.{UByte, UInt}

case class OTFInt16(value: Short) extends SectionDataType {

  override def bytes(b: ByteAllocator): Array[UByte] = {
    Array(((value & 0xFF00) >> 8).toByte, (value & 0x00FF).toByte).map(UByte(_))
  }

  override def length(b: ByteAllocator): UInt = UInt(2)

  /**
    * Gets data sections if this data block has offsets
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator) = Seq()

}
