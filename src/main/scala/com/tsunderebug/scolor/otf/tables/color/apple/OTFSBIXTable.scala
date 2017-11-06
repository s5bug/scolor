package com.tsunderebug.scolor.otf.tables.color.apple

import com.tsunderebug.scolor.ByteAllocator
import com.tsunderebug.scolor.otf.tables.OpenTypeTable
import com.tsunderebug.scolor.otf.types.{OTFArray, OTFUInt16, OTFUInt32}
import com.tsunderebug.scolor.table.Section
import spire.math.{UInt, UShort}

case class OTFSBIXTable(
                         sds: Traversable[OTFAppleStrikeData]
                       ) extends OpenTypeTable {

  /**
    * @return the table tag/name/identifier
    */
  override def name = "sbix"

  /**
    * @param b The byte allocator
    * @return the sections/partitions/rows of a table
    */
  override def sections(b: ByteAllocator) = Seq(
    Section("version", OTFUInt16(UShort(1))),
    Section("flags", OTFUInt16(UShort(2))),
    Section("numStrikes", OTFUInt32(UInt(sds.size))),
    Section("offsets", OTFArray(data(b).map((d) => OTFUInt32(b.allocate(d).position - b.allocate(this).position))))
  )

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator): Traversable[OTFAppleStrikeData] = sds

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator): UInt = UInt(8) + (UInt(4) * UInt(sds.size))

}
