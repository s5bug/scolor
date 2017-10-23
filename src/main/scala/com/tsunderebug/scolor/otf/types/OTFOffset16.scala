package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.{ByteAllocator, Offset}
import spire.math.{UByte, UInt, UShort}

case class OTFOffset16(offset: Int) extends Offset {

  override def position: UInt = UInt(offset)

  override def bytes(b: ByteAllocator): Array[UByte] = OTFUInt16(UShort(offset)).bytes(b)

  override def length(b: ByteAllocator) = UInt(2)

  /**
    * Gets data sections if this data block has offsets
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator) = Seq()

}
