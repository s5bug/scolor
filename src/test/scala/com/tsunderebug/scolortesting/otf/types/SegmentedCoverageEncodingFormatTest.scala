package com.tsunderebug.scolor.otf.types

import org.scalatest.{FlatSpec, OptionValues}
import spire.math.UInt

import com.tsunderebug.scolor.otf.{OTFByteAllocator, OpenTypeFont}

class SegmentedCoverageEncodingFormatTest extends FlatSpec with OptionValues {

	private def byteAllocator = new OTFByteAllocator(OpenTypeFont(Nil))

	"SegmentedCoverageEncodingFormat" should "return its groups sorted when sections are retrieved" in {
    val groupsOutOfOrder = for (i <- (0 to 10 by 2).reverse) yield SequentialMapGroup(UInt(i), UInt(i +1), UInt(0))
		val sections = OTFEncodingRecord.SegmentedCoverageEncodingFormat(groupsOutOfOrder).sections(byteAllocator)

		val otfArray = sections.find(_.name == "groups[numGroups]").value.data match {
			case o: OTFArray[_] => o
			case other => fail(s"Expected an OTFArray for section with name groups[numGroups] but got ${other}")
		}
		
		assertResult(groupsOutOfOrder.sorted) {
			otfArray.elems
		}

	}
}