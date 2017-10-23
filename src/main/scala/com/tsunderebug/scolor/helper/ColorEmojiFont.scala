package com.tsunderebug.scolor.helper

import com.tsunderebug.scolor.Models.Codepoint
import com.tsunderebug.scolor.otf.OpenTypeFont
import com.tsunderebug.scolor.otf.tables.color.OTFCBDTTable
import com.tsunderebug.scolor.otf.tables.{OTFCMAPTable, OTFNAMETable}
import com.tsunderebug.scolor.otf.types.gen.{MacLanguage, WindowsLanguage}
import com.tsunderebug.scolor.otf.types.{OTFEncodingRecord, OTFNameRecord, OTFString, SequentialMapGroup}
import spire.math.{UInt, UShort}

case class ColorEmojiFont(
                           internationalName: String,
                           entries: Map[Codepoint, ColorEmojiEntry]
                         ) extends

  OpenTypeFont(
    Seq(
      OTFNAMETable(
        Seq(
          OTFNameRecord(
            d = WindowsLanguage.`English`.`United States`,
            content = OTFString(internationalName)
          ),
          OTFNameRecord(
            d = MacLanguage(MacLanguage.Script.`Roman`, MacLanguage.Language.`English`),
            content = OTFString(internationalName)
          )
        )
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
        entries.values.flatMap(_.bitmaps.values)
      )
    )
  )