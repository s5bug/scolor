package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.ByteAllocator
import com.tsunderebug.scolor.table.SectionDataType
import spire.math.{UByte, UInt}

case class OTFUInt32(value: UInt) extends SectionDataType {

  override def getBytes(b: ByteAllocator): Array[UByte] = {
    Array(((value.toInt & 0xFF000000) >> 24).toByte, ((value.toInt & 0x00FF0000) >> 16).toByte, ((value.toInt & 0x0000FF00) >> 8).toByte, (value.toInt & 0x000000FF).toByte).map(UByte(_))
  }

  override def length(b: ByteAllocator) = UInt(4)

  /**
    * Gets data sections if this data block has offsets
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def getData(b: ByteAllocator) = Seq()

}