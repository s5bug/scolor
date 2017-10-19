package com.tsunderebug.scolor.otf.types

import org.scalatest.{FlatSpec, OptionValues}
import spire.math.{UInt, UShort}

import com.tsunderebug.scolor.otf.{OTFByteAllocator, OpenTypeFont}

class SegmentedCoverageEncodingFormatTest extends FlatSpec with OptionValues {

  private def byteAllocator = new OTFByteAllocator(OpenTypeFont(Nil))

  "SegmentedCoverageEncodingFormat" should "return its groups sorted when sections are retrieved" in {
    val groupsOutOfOrder = for (i <- (10 to 0 by -2).reverse) yield SequentialMapGroup(UInt(i), UInt(i +1), UInt(0))
    val sections = OTFEncodingRecord.SegmentedCoverageEncodingFormat(groupsOutOfOrder).sections(byteAllocator)

    val otfArray = sections.find(_.name == "groups[numGroups]").value.data match {
      case o: OTFArray[_] => o
      case other => fail(s"Expected an OTFArray for section with name groups[numGroups] but got ${other}")
    }
    
    assertResult(groupsOutOfOrder.sorted) {
      otfArray.elems
    }
  }

  it should "have a format of 12 as required by the specification" in {
    val sections = OTFEncodingRecord.SegmentedCoverageEncodingFormat(Nil).sections(byteAllocator)
    assertResult(OTFUInt16(UShort(12)))(sections.find(_.name == "format").value.data)
  }

  it should "have a reserved section with a 0 value as required by the specification" in {
    val sections = OTFEncodingRecord.SegmentedCoverageEncodingFormat(Nil).sections(byteAllocator)
    assertResult(OTFUInt16(UShort(0)))(sections.find(_.name == "reserved").value.data)  
  }

  private val headerSize = UInt(16)

  it should "have a length reflecting the header section and its groups" in {
    info("checking case when there are no groups")
    val sectionsNoGroups = OTFEncodingRecord.SegmentedCoverageEncodingFormat(Nil).sections(byteAllocator)
    assertResult(OTFUInt32(headerSize))(sectionsNoGroups.find(_.name == "length").value.data)

    info("checking case when there are groups")
    val groups = for (i <- (0 to 10 by 2).reverse) yield SequentialMapGroup(UInt(i), UInt(i +1), UInt(0))
    val groupSize = groups.foldLeft(UInt(0)) {
      case (accum, group) => accum + group.length(byteAllocator)
    }
    val sectionsWithGroups = OTFEncodingRecord.SegmentedCoverageEncodingFormat(groups).sections(byteAllocator)
    assertResult(OTFUInt32(headerSize + groupSize)) {
      sectionsWithGroups.find(_.name == "length").value.data
    }
    
    assertResult(OTFUInt32(UInt(groups.size))) {
      sectionsWithGroups.find(_.name == "numGroups").value.data
    }
  }

  it should "retrieve glyphs based on the groups provided to it" in {
    val groups = for (i <- (0 to 6 by 2).reverse) yield SequentialMapGroup(UInt(i), UInt(i +1), UInt(0))
    val expectedMap: Map[UInt, UInt] = Map(
      0 -> 0,
      1 -> 1,
      2 -> 0,
      3 -> 1,
      4 -> 0,
      5 -> 1,
      6 -> 0,
      7 -> 1
    ).map {
      case (k,v) => UInt(k) -> UInt(v)
    }

    assertResult(expectedMap) {
      OTFEncodingRecord.SegmentedCoverageEncodingFormat(groups).getGlyphEntries
    }
  }
}