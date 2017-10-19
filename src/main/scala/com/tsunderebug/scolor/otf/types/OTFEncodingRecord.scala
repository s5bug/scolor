package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.Models.{Codepoint, GlyphID}
import com.tsunderebug.scolor._
import com.tsunderebug.scolor.otf.tables.OTFCMAPTable
import com.tsunderebug.scolor.otf.types.OTFEncodingRecord.EncodingFormat
import com.tsunderebug.scolor.table.{EnclosingSectionDataType, RequireTable, Section}
import spire.math.{UInt, UShort}

private[scolor] class TabledEncodingRecord(platformID: UShort, encodingID: UShort, encodingFormat: EncodingFormat, table: OTFCMAPTable) extends EnclosingSectionDataType {

  override def sections(b: ByteAllocator): Seq[Section] = Seq(
    Section("platformID", OTFUInt16(platformID)),
    Section("encodingID", OTFUInt16(encodingID)),
    Section("offset", OTFOffset16((b.allocate(encodingFormat).position - b.allocate(table).position).toInt))
  )

  override def length(b: ByteAllocator) = UInt(8)

  override def getData(b: ByteAllocator): Seq[Data] = Seq(encodingFormat)

}

case class OTFEncodingRecord(platformID: UShort, encodingID: UShort, encodingFormat: EncodingFormat) extends RequireTable[OTFCMAPTable, TabledEncodingRecord] {

  override def apply(t: OTFCMAPTable): TabledEncodingRecord = new TabledEncodingRecord(platformID, encodingID, encodingFormat, t)

}

object OTFEncodingRecord {

  trait EncodingFormat extends EnclosingSectionDataType {

    def getGlyphEntries: Map[Models.Codepoint, GlyphID]

  }

  /**
    * This is the Microsoft standard character-to-glyph-index mapping table for fonts supporting Unicode
    * supplementary-plane characters (U+10000 to U+10FFFF). This is the main format used as it can support all defined
    * characters.
    */
  case class SegmentedCoverageEncodingFormat(smg: Seq[SequentialMapGroup]) extends EncodingFormat {

    /** Retrieve a map of codepoints to glyph IDs based on the SequentialMapGroup of this instance 
     * @note Regardless of the encoding scheme, character codes that do not correspond to any glyph in the font are mapped to glyph index 0. The glyph at this location must be a special glyph representing a missing character, commonly known as .notdef.
     * @return Map of codepoints to glyph ids, as noted any glyphs not defined for a given character code will be 0
     */
    override def getGlyphEntries: Map[Codepoint, GlyphID] = {
      val emptyGlyph = UInt(0)
      smg.foldLeft(Map.empty[Codepoint, GlyphID]) {
        case (accum, SequentialMapGroup(startCharCode, endCharCode, startGlyphID)) => {
          val glyphRange = (startGlyphID.toLong to (startGlyphID + endCharCode - startCharCode).toLong)
          accum ++ (startCharCode.toLong to endCharCode.toLong).zipWithIndex.map {
            case (charPoint, index) => UInt(charPoint) -> glyphRange.lift(index).fold(emptyGlyph)(UInt.apply)
          }
        }
      }
    }

    override def sections(b: ByteAllocator): Seq[Section] = Seq(
      Section("format", OTFUInt16(UShort(12))),
      Section("reserved", OTFUInt16(UShort(0))),
      Section("length", OTFUInt32(length(b)))
    )

    /**
      * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
      *
      * @param b The byte allocator
      * @return an unsigned integer describing the length of this data block
      */
    override def length(b: ByteAllocator): Codepoint = UInt(16 + smg.length * 12)

    /**
      * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
      *
      * @param b The byte allocator
      * @return an array of Data objects
      */
    override def getData(b: ByteAllocator): Seq[Data] = Seq()

  }

}

case class SequentialMapGroup(startCodepoint: Codepoint, endCodepoint: Codepoint, startGlyphID: GlyphID) extends EnclosingSectionDataType {

  override def sections(b: ByteAllocator) = Seq(
    Section("startCharCode", OTFUInt32(startCodepoint)),
    Section("endCharCode", OTFUInt32(endCodepoint)),
    Section("startGlyphID", OTFUInt32(startGlyphID))
  )

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator) = UInt(12)

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def getData(b: ByteAllocator) = Seq()

}
