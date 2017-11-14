package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.ByteAllocator
import com.tsunderebug.scolor.table.SectionDataType
import spire.math.{UByte, UInt}

case class OTFString(s: String) extends SectionDataType {

  override def length(b: ByteAllocator): UInt = UInt(s.getBytes("UTF-16BE").length)

  /**
    * Gets data sections if this data block has offsets
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator) = Seq()

  /**
    * @param b The byte allocator
    * @return an array of unsigned bytes representing the data.
    */
  override def bytes(b: ByteAllocator): Array[UByte] = s.getBytes("UTF-16BE").map(UByte(_))

}
