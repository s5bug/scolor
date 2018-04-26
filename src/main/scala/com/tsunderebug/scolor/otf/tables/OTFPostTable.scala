package com.tsunderebug.scolor.otf.tables

import com.tsunderebug.scolor.otf.types.OTFFixedPoint
import com.tsunderebug.scolor.otf.types.num.{OTFInt16, OTFUInt32}
import com.tsunderebug.scolor.table.Section
import com.tsunderebug.scolor.{ByteAllocator, Data}
import spire.math.UInt

case class OTFPostTable(
                         italicAngle: Double,
                         underlinePosition: Short,
                         underlineThickness: Short,
                         isFixedPitch: UInt = UInt(0),
                         minMemType42: UInt = UInt(0),
                         maxMemType42: UInt = UInt(0),
                         minMemType1: UInt = UInt(0),
                         maxMemType1: UInt = UInt(0)
                       ) extends OpenTypeTable {
  /**
    * @return the table tag/name/identifier
    */
  override def name: String = "post"

  /**
    * @param b The byte allocator
    * @return the sections/partitions/rows of a table
    */
  override def sections(b: ByteAllocator): Traversable[Section] = Seq(
    Section("version", OTFFixedPoint(3.0)),
    Section("italicAngle", OTFFixedPoint(italicAngle)),
    Section("underlinePosition", OTFInt16(underlinePosition)),
    Section("underlineThickness", OTFInt16(underlineThickness)),
    Section("isFixedPitch", OTFUInt32(isFixedPitch)),
    Section("minMemType42", OTFUInt32(minMemType42)),
    Section("maxMemType42", OTFUInt32(maxMemType42)),
    Section("minMemType1", OTFUInt32(minMemType1)),
    Section("maxMemType1", OTFUInt32(maxMemType1))
  )

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator): UInt = UInt(32)

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator): Traversable[Data] = Seq()
}
