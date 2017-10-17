package com.tsunderebug.scolor.otf.tables

import com.tsunderebug.scolor.ByteAllocator
import com.tsunderebug.scolor.otf.types.{OTFEncodingRecord, OTFArray, TabledEncodingRecord, OTFUInt16}
import com.tsunderebug.scolor.table.Section
import spire.math.{UInt, UShort}

case class OTFCMAPTable(
                       encodingRecords: Seq[OTFEncodingRecord]
                       ) extends OpenTypeTable {

  override def name = "cmap"

  private val tabledRecords: Seq[TabledEncodingRecord] = encodingRecords.map(_(this))

  override def sections(b: ByteAllocator): Seq[Section] = Seq(
    Section("version", OTFUInt16(UShort(0))),
    Section("numTables", OTFUInt16(UShort(encodingRecords.length))), // TODO CMAP encoding class
    Section("data", OTFArray(tabledRecords))
  )

  override def length(b: ByteAllocator): UInt = {
    UInt(sections(b).map(_.data.length(b).toLong).sum)
  }

  /**
    * Gets data sections if this data block has offsets
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def getData(b: ByteAllocator) = Seq()

}
