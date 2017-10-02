package com.tsunderebug.scolor.otf.tables

import com.tsunderebug.scolor.Font
import com.tsunderebug.scolor.otf.types.{EncodingRecord, UInt16}
import com.tsunderebug.scolor.table.{Section, Table}
import spire.math.{UByte, UInt, UShort}

case class OpenTypeCMAPTable(
                       encodingRecords: Seq[EncodingRecord]
                       ) extends Table {

  override def name = "cmap"

  override def sections: Seq[Section] = Seq(
    Section("version", UInt16(UShort(0))),
    Section("numTables", UInt16(UShort(encodingRecords.length))) // TODO CMAP encoding class
  )

  override def getBytes(f: Font): Array[UByte] = sections.flatMap(_.getBytes(f)).toArray

  override def length: UInt = {
    UInt(sections.map(_.data.length.toLong).sum)
  }

  /**
    * Gets data sections if this data block has offsets
    *
    * @param f The font
    * @return an array of Data objects
    */
  override def data(f: Font) = Array()

}
