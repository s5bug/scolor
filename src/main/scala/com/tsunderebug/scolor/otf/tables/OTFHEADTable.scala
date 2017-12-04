package com.tsunderebug.scolor.otf.tables

import java.time.{LocalDateTime, ZoneOffset}

import com.tsunderebug.scolor.otf.types.OTFFixedPoint
import com.tsunderebug.scolor.otf.types.num.{OTFInt16, OTFInt64, OTFUInt16, OTFUInt32}
import com.tsunderebug.scolor.table.Section
import com.tsunderebug.scolor.{ByteAllocator, Data}
import spire.math.{UInt, UShort}

case class OTFHEADTable(
                         fontRevision: Double,
                         flags: UShort,
                         unitsPerEm: UShort,
                         private val _created: Long,
                         private val _modified: Long,
                         xMin: Short,
                         yMin: Short,
                         xMax: Short,
                         yMax: Short,
                         macStyle: UShort,
                         lowestRecPPEM: UShort,
                         indexToLocFormat: Short,
                         checkSumAdjustment: UInt = UInt(0)
                       ) extends OpenTypeTable {

  val created: Long = OTFHEADTable.timeOffset + _created
  val modified: Long = OTFHEADTable.timeOffset + _modified

  /**
    * @return the table tag/name/identifier
    */
  override def name: String = "head"

  /**
    * @param b The byte allocator
    * @return the sections/partitions/rows of a table
    */
  override def sections(b: ByteAllocator): Traversable[Section] = Seq(
    Section("majorVersion", OTFUInt16(UShort(1))),
    Section("minorVersion", OTFUInt16(UShort(0))),
    Section("fontRevision", OTFFixedPoint(fontRevision)),
    Section("checkSumAdjustment", OTFUInt32(checkSumAdjustment)),
    Section("magicNumber", OTFUInt32(UInt(0x5F0F3CF5))),
    Section("flags", OTFUInt16(flags)),
    Section("unitsPerEm", OTFUInt16(unitsPerEm)),
    Section("created", OTFInt64(created)),
    Section("modified", OTFInt64(modified)),
    Section("xMin", OTFInt16(xMin)),
    Section("yMin", OTFInt16(yMin)),
    Section("xMax", OTFInt16(xMax)),
    Section("yMax", OTFInt16(yMax)),
    Section("macStyle", OTFUInt16(macStyle)),
    Section("lowestRecPPEM", OTFUInt16(lowestRecPPEM)),
    Section("fontDirectionHint", OTFInt16(2)),
    Section("indexToLocFormat", OTFInt16(indexToLocFormat))
  )

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator): UInt = UInt(52)

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator): Traversable[Data] = Seq()

}

object OTFHEADTable {

  val timeOffset: Long = -LocalDateTime.of(1904, 1, 1, 0, 0, 0, 0).toEpochSecond(ZoneOffset.UTC)

}
