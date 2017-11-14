package com.tsunderebug.scolor.otf.tables.color.apple

import com.tsunderebug.scolor.ByteAllocator
import com.tsunderebug.scolor.otf.types.OTFArray
import com.tsunderebug.scolor.otf.types.num.{OTFUInt16, OTFUInt32}
import com.tsunderebug.scolor.table.{EnclosingSectionDataType, Section}
import spire.math.{UInt, UShort}

case class OTFAppleStrikeData(
                               ppem: UShort,
                               resolution: UShort,
                               gds: Traversable[OTFAppleGlyphData]
                             ) extends EnclosingSectionDataType {

  override def sections(b: ByteAllocator) = Seq(
    Section("ppem", OTFUInt16(ppem)),
    Section("resolution", OTFUInt16(resolution)),
    Section("offsets", OTFArray(data(b).map((d) => OTFUInt32(b.allocate(d).position - b.allocate(this).position))))
  )

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator): Traversable[OTFAppleGlyphData] = gds

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator): UInt = UInt(4) + (UInt(4) * UInt(gds.size))

}
