package com.tsunderebug.scolor.helper

import java.time.Instant

import com.tsunderebug.scolor.Models.Codepoint
import com.tsunderebug.scolor.otf.OpenTypeFont
import com.tsunderebug.scolor.otf.tables.color.apple.{OTFAppleStrikeData, OTFSBIXTable}
import com.tsunderebug.scolor.otf.tables.color.google.OTFCBDTTable
import com.tsunderebug.scolor.otf.tables.color.svg.{OTFSVGDocumentIndex, OTFSVGDocumentIndexEntry, OTFSVGTable}
import com.tsunderebug.scolor.otf.tables.os2.{OTFCodePageRange, OTFOS2Table, OTFPANOSEClassification, OTFUnicodeRange}
import com.tsunderebug.scolor.otf.tables.{OTFCMAPTable, OTFHEADTable, OTFNAMETable}
import com.tsunderebug.scolor.otf.types.gen.{MacLanguage, WindowsLanguage}
import com.tsunderebug.scolor.otf.types.num.OTFUInt16
import com.tsunderebug.scolor.otf.types.{OTFEncodingRecord, OTFNameRecord, SequentialMapGroup}
import spire.math.{UByte, UInt, UShort}

case class ColorEmojiFont(
                           pixelsPerEm: UShort,
                           internationalName: String,
                           version: Double,
                           created: Long,
                           entries: Map[Codepoint, ColorEmojiEntry]
                         ) extends

  OpenTypeFont(
    Seq(
      OTFNAMETable(
        Seq(
          OTFNameRecord(
            d = WindowsLanguage.`English`.`United States`,
            content = internationalName
          ),
          OTFNameRecord(
            d = MacLanguage(MacLanguage.Script.`Roman`, MacLanguage.Language.`English`),
            content = internationalName
          )
        )
      ),
      OTFHEADTable(
        version,
        UShort(0),
        UShort(256),
        created,
        Instant.now().getEpochSecond,
        0, 0, 256, 256,
        UShort(0),
        UShort(8),
        1
      ),
      OTFOS2Table(
        256,
        UShort(400),
        UShort(5),
        UShort(0),
        128,
        128,
        0,
        -64,
        128,
        128,
        0,
        192,
        256,
        128,
        0,
        OTFPANOSEClassification(
          UByte(5),
          UByte(0),
          UByte(1),
          UByte(0),
          UByte(1),
          UByte(0),
          UByte(0),
          UByte(0),
          UByte(0),
          UByte(0)
        ),
        new OTFUnicodeRange.OTFUnicodeRangeFlag,
        internationalName.toLowerCase,
        UShort(1 << 6),
        UShort(0xFFFF),
        UShort(0xFFFF),
        0,
        0,
        0,
        UShort(256),
        UShort(-0),
        new OTFCodePageRange.OTFCodePageRangeFlag,
        0,
        0,
        UShort(0),
        UShort(0),
        UShort(0),
        UShort(0),
        UShort(0)
      ),
      OTFCMAPTable(
        Seq(
          OTFEncodingRecord(
            UShort(3),
            UShort(1),
            OTFEncodingRecord.SegmentedCoverageEncodingFormat(
              entries.keys.zipWithIndex.map {
                case (c, i) =>
                  SequentialMapGroup(c, c, UInt(i + 1))
              }
            )
          ),
          OTFEncodingRecord(
            UShort(1),
            UShort(0),
            OTFEncodingRecord.SegmentedCoverageEncodingFormat(
              entries.keys.zipWithIndex.map {
                case (c, i) =>
                  SequentialMapGroup(c, c, UInt(i + 1))
              }
            )
          )
        )
      ),
      OTFCBDTTable(
        entries.values.flatMap(_.bitmaps.values.map(_._1))
      ),
      OTFSBIXTable(
        Seq(OTFAppleStrikeData(pixelsPerEm, UShort(300), entries.values.flatMap(_.bitmaps.values.map(_._2))))
      ),
      OTFSVGTable(
        OTFSVGDocumentIndex(
          entries.values.flatMap {
            entry =>
              entry.toScalable.values.zipWithIndex.map {
                case (doc, index) =>
                  OTFSVGDocumentIndexEntry(
                    OTFUInt16(UShort(index)),
                    OTFUInt16(UShort(index)),
                    doc
                  )
              }
          }
        )
      )
    )
  )
