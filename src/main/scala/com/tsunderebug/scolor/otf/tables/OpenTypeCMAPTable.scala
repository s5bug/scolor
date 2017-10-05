package com.tsunderebug.scolor.otf.tables

import com.tsunderebug.scolor.Font
import com.tsunderebug.scolor.otf.types.{EncodingRecord, OTFArray, TabledEncodingRecord, UInt16}
import com.tsunderebug.scolor.table.Section
import spire.math.{UInt, UShort}

case class OpenTypeCMAPTable(
                       encodingRecords: Seq[EncodingRecord]
                       ) extends OpenTypeTable {

  override def name = "cmap"

  private val tabledRecords: Seq[TabledEncodingRecord] = encodingRecords.map(_(this))

  override def sections(f: Font): Seq[Section] = Seq(
    Section("version", UInt16(UShort(0))),
    Section("numTables", UInt16(UShort(encodingRecords.length))), // TODO CMAP encoding class
    Section("data", OTFArray(tabledRecords))
  )

  override def length(f: Font): UInt = {
    UInt(sections(f).map(_.data.length(f).toLong).sum)
  }

  /**
    * Gets data sections if this data block has offsets
    *
    * @param f The font
    * @return an array of Data objects
    */
  override def getData(f: Font) = Array()

}
