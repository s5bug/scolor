package com.tsunderebug.scolor.otf.types

import org.scalatest.{FlatSpec, OptionValues}
import spire.math.UInt

import com.tsunderebug.scolor.otf.{OTFByteAllocator, OpenTypeFont}

class SequentialMapGroupTest() extends FlatSpec with OptionValues {

  private def byteAllocator = new OTFByteAllocator(OpenTypeFont(Nil))

  "SequentialMapGroup" should "be sorted naturally by their starting character code" in {
      val range = (0 to 10 by 2)
      val incorrectlyOrdered = for (i <- range.reverse) yield SequentialMapGroup(UInt(i), UInt(i +1), UInt(0))
      val correctlyOrdered = for (i <- range) yield SequentialMapGroup(UInt(i), UInt(i +1), UInt(0))
      assertResult(correctlyOrdered) {
        incorrectlyOrdered.sorted
      }
    }

    it should "have a length of 12 bytes (because SequentialMapGroup has 3 sections of 4 bytes each)" in {
      assertResult(UInt(12)) {
        SequentialMapGroup(UInt(1), UInt(5), UInt(0)).length(byteAllocator)
      }
    }

    it should "have start and end codepoints and the glyph ID in its sections" in {
      val startCodePoint = UInt(1)
      val endCodePoint = UInt(3)
      val glyphID = UInt(12)
      val sections = SequentialMapGroup(startCodePoint, endCodePoint, glyphID).sections(byteAllocator)
      assertResult(OTFUInt32(startCodePoint)) {
        sections.find(_.name == "startCharCode").value.data
      }
      assertResult(OTFUInt32(endCodePoint)) {
        sections.find(_.name == "endCharCode").value.data
      }
      assertResult(OTFUInt32(glyphID)) {
        sections.find(_.name == "startGlyphID").value.data
      }
    }
}
