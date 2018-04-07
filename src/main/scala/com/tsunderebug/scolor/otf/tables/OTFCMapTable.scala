package com.tsunderebug.scolor.otf.tables

import com.tsunderebug.scolor.ByteAllocator
import com.tsunderebug.scolor.otf.types.num.OTFUInt16
import com.tsunderebug.scolor.otf.types.{OTFArray, OTFEncodingRecord, TabledEncodingRecord}
import com.tsunderebug.scolor.table.Section
import spire.math.{UInt, UShort}

case class OTFCMapTable(
                         encodingRecords: Traversable[OTFEncodingRecord]
                       ) extends OpenTypeTable {

  override def name = "cmap"

  private val tabledRecords: Traversable[TabledEncodingRecord] = encodingRecords.map(_ (this))

  override def sections(b: ByteAllocator): Traversable[Section] = Seq(
    Section("version", OTFUInt16(UShort(0))),
    Section("numTables", OTFUInt16(UShort(encodingRecords.size))),
    Section("data", OTFArray(tabledRecords))
  )

  override def length(b: ByteAllocator): UInt = {
    sections(b).foldLeft(UInt(0)) {
      case (accum, section) => accum + section.data.length(b)
    }
  }

  /**
    * Gets data sections if this data block has offsets
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator) = Seq()

}
