package com.tsunderebug.scolor.otf.tables.color.google

import com.tsunderebug.scolor.ByteAllocator
import com.tsunderebug.scolor.otf.types.num.{OTFInt8, OTFUInt8}
import com.tsunderebug.scolor.table.Section
import spire.math.{UByte, UInt}

case class OTFGoogleSmallGlyphMetrics(
                                       height: UByte,
                                       width: UByte,
                                       bearingX: Byte,
                                       bearingY: Byte,
                                       advance: UByte
                                     ) extends OTFGoogleGlyphMetrics {

  override def sections(b: ByteAllocator) = Seq(
    Section("height", OTFUInt8(height)),
    Section("width", OTFUInt8(width)),
    Section("bearingX", OTFInt8(bearingX)),
    Section("bearingY", OTFInt8(bearingY)),
    Section("advance", OTFUInt8(advance))
  )

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator) = UInt(5)

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator) = Seq()

}
