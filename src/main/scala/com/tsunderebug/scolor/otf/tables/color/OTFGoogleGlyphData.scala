package com.tsunderebug.scolor.otf.tables.color

import com.tsunderebug.scolor.ByteAllocator
import com.tsunderebug.scolor.table.{EnclosingSectionDataType, Section}
import spire.math.UInt

case class OTFGoogleGlyphData(
                               metrics: OTFGoogleGlyphMetrics,
                               img: OTFsRGBPNG
                             ) extends EnclosingSectionDataType {

  override def sections(b: ByteAllocator) = Seq(
    Section("metrics", metrics),
    Section("data", img)
  )

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator): UInt = metrics.length(b) + img.length(b)

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator) = Seq()

}
