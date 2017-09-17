package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.{Codepoint, Font, Glyph}
import com.tsunderebug.scolor.table.{EnclosingSectionDataType, Section}
import spire.math.{UByte, UInt, UShort}

case class EncodingRecord(platformID: UShort, encodingID: UShort) extends EnclosingSectionDataType {

  override def sections(f: Font) = Seq(
    Section("platformID", UInt16(platformID)),
    Section("encodingID", UInt16(encodingID)),
    Section("offset", f.nextAvailableOffset(UInt(0)))
  )

  trait EncodingFormat {
    def addGlyphEntry(c: Seq[Codepoint], g: Glyph): Unit
    def getGlyphEntries: Map[Seq[Codepoint], Glyph]
    def getLength: UInt
    def getBytes: Array[UByte]
  }

}
