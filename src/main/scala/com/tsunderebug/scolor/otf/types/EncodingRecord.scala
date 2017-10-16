package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.otf.tables.OpenTypeCMAPTable
import com.tsunderebug.scolor.otf.types.EncodingRecord.EncodingFormat
import com.tsunderebug.scolor.table.{EnclosingSectionDataType, RequireTable, Section}
import com.tsunderebug.scolor._
import spire.math.{UByte, UInt, UShort}

private[scolor] class TabledEncodingRecord(platformID: UShort, encodingID: UShort, encodingFormat: EncodingFormat, table: OpenTypeCMAPTable) extends EnclosingSectionDataType {

  override def sections(b: ByteAllocator): Seq[Section] = Seq(
    Section("platformID", UInt16(platformID)),
    Section("encodingID", UInt16(encodingID)),
    Section("offset", Offset16((b.allocate(encodingFormat).position - b.allocate(table).position).toInt))
  )

  override def length(b: ByteAllocator) = UInt(8)

  override def getData(b: ByteAllocator): Seq[Data] = Seq(encodingFormat)

}

case class EncodingRecord(platformID: UShort, encodingID: UShort, encodingFormat: EncodingFormat) extends RequireTable[OpenTypeCMAPTable, TabledEncodingRecord] {

  override def apply(t: OpenTypeCMAPTable): TabledEncodingRecord = new TabledEncodingRecord(platformID, encodingID, encodingFormat, t)

}

object EncodingRecord {

  trait EncodingFormat extends EnclosingSectionDataType {

    /**
      * For mapping multiple characters to one glyph, see the GSUB table.
      * @param c The codepoint
      * @param g The glyph
      */
    def addGlyphEntry(c: Models.Codepoint, g: Glyph): Unit

    def getGlyphEntries: Map[Models.Codepoint, Glyph]

    def getLength: UInt

    def getBytes: Array[UByte]
  }

}
