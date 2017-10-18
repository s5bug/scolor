package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.{ByteAllocator, Offset}
import spire.math.{UByte, UInt}

case class OTFOffset32(offset: Long) extends Offset {

  override def position: UInt = UInt(offset)

  override def getBytes(b: ByteAllocator): Array[UByte] = OTFUInt32(UInt(offset)).getBytes(b)

  override def length(b: ByteAllocator) = UInt(4)

  /**
    * Gets data sections if this data block has offsets
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def getData(b: ByteAllocator) = Seq()

}
