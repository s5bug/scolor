package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.otf.types.EncodingRecord.EncodingFormat
import com.tsunderebug.scolor.{Codepoint, Data, Font, Glyph}
import com.tsunderebug.scolor.table.{EnclosingSectionDataType, Section, SectionDataType}
import spire.math.{UByte, UInt, UShort}

case class EncodingRecord(platformID: UShort, encodingID: UShort, encodingFormat: EncodingFormat) extends EnclosingSectionDataType {

  override def sections(f: Font) = Seq(
    Section("platformID", UInt16(platformID)),
    Section("encodingID", UInt16(encodingID)),
    Section("offset", f.allocate(getData(f)(0)))
  )

  override def length(f: Font) = UInt(8)

  override def getData(f: Font): Array[Data] = Array(encodingFormat)

}

object EncodingRecord {

  trait EncodingFormat extends SectionDataType {
    def addGlyphEntry(c: Seq[Codepoint], g: Glyph): Unit
    def getGlyphEntries: Map[Seq[Codepoint], Glyph]
    def getLength: UInt
    def getBytes: Array[UByte]
  }

}
