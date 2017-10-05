package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.otf.tables.OpenTypeCMAPTable
import com.tsunderebug.scolor.otf.types.EncodingRecord.EncodingFormat
import com.tsunderebug.scolor.table.{EnclosingSectionDataType, RequireTable, Section}
import com.tsunderebug.scolor.{Codepoint, Data, Font, Glyph}
import spire.math.{UByte, UInt, UShort}

private[scolor] class TabledEncodingRecord(platformID: UShort, encodingID: UShort, encodingFormat: EncodingFormat, table: OpenTypeCMAPTable) extends EnclosingSectionDataType {

  override def sections(f: Font): Seq[Section] = Seq(
    Section("platformID", UInt16(platformID)),
    Section("encodingID", UInt16(encodingID)),
    Section("offset", Offset16((f.allocate(encodingFormat).position - f.allocate(table).position).toInt))
  )

  override def length(f: Font) = UInt(8)

  override def getData(f: Font): Array[Data] = Array(encodingFormat)

}

case class EncodingRecord(platformID: UShort, encodingID: UShort, encodingFormat: EncodingFormat) extends RequireTable[OpenTypeCMAPTable, TabledEncodingRecord] {

  override def apply(t: OpenTypeCMAPTable): TabledEncodingRecord = new TabledEncodingRecord(platformID, encodingID, encodingFormat, t)

}

object EncodingRecord {

  trait EncodingFormat extends EnclosingSectionDataType {
    def addGlyphEntry(c: Seq[Codepoint], g: Glyph): Unit

    def getGlyphEntries: Map[Seq[Codepoint], Glyph]

    def getLength: UInt

    def getBytes: Array[UByte]
  }

}
