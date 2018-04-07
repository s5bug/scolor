package com.tsunderebug.scolor.otf.tables.os2

import com.tsunderebug.scolor.otf.tables.OpenTypeTable
import com.tsunderebug.scolor.otf.types.OTFString
import com.tsunderebug.scolor.otf.types.num.{OTFInt16, OTFUInt16}
import com.tsunderebug.scolor.table.Section
import com.tsunderebug.scolor.{ByteAllocator, Data}
import spire.math.{UInt, UShort}

case class OTFOS2Table(
                        xAvgCharWidth: Short,
                        usWeightClass: UShort,
                        usWidthClass: UShort,
                        fsType: UShort,
                        ySubscriptXSize: Short,
                        ySubscriptYSize: Short,
                        ySubscriptXOffset: Short,
                        ySubscriptYOffset: Short,
                        ySuperscriptXSize: Short,
                        ySuperscriptYSize: Short,
                        ySuperscriptXOffset: Short,
                        ySuperscriptYOffset: Short,
                        yStrikeoutSize: Short,
                        yStrikeoutPosition: Short,
                        sFamilyClass: Short,
                        panose: OTFPANOSEClassification,
                        ulUnicodeRange: OTFUnicodeRange.OTFUnicodeRangeFlag,
                        private val _tag: String,
                        fsSelection: UShort,
                        usFirstCharIndex: UShort,
                        usLastCharIndex: UShort,
                        sTypoAscender: Short,
                        sTypoDescender: Short,
                        sTypoLineGap: Short,
                        usWinAscent: UShort,
                        usWinDescent: UShort,
                        ulCodePageRange: OTFCodePageRange.OTFCodePageRangeFlag,
                        sxHeight: Short,
                        sCapHeight: Short,
                        usDefaultChar: UShort,
                        usBreakChar: UShort,
                        usMaxContext: UShort,
                        usLowerOpticalPointSize: UShort,
                        usUpperOpticalPointSize: UShort
                      ) extends OpenTypeTable {
  val tag: String = _tag.take(4).padTo(4, ' ')

  /**
    * @return the table tag/name/identifier
    */
  override def name: String = "OS/2"

  /**
    * @param b The byte allocator
    * @return the sections/partitions/rows of a table
    */
  override def sections(b: ByteAllocator): Traversable[Section] = Seq(
    Section("version", OTFUInt16(UShort(5))),
    Section("xAvgCharWidth", OTFInt16(xAvgCharWidth)),
    Section("usWeightClass", OTFUInt16(usWeightClass)),
    Section("usWidthClass", OTFUInt16(usWidthClass)),
    Section("fsType", OTFUInt16(fsType)),
    Section("ySubscriptXSize", OTFInt16(ySubscriptXSize)),
    Section("ySubscriptYSize", OTFInt16(ySubscriptYSize)),
    Section("ySubscriptXOffset", OTFInt16(ySubscriptXOffset)),
    Section("ySubscriptYOffset", OTFInt16(ySubscriptYOffset)),
    Section("ySuperscriptXSize", OTFInt16(ySuperscriptXSize)),
    Section("ySuperscriptYSize", OTFInt16(ySuperscriptYSize)),
    Section("ySuperscriptXOffset", OTFInt16(ySuperscriptXOffset)),
    Section("ySuperscriptYOffset", OTFInt16(ySuperscriptYOffset)),
    Section("yStrikeoutSize", OTFInt16(yStrikeoutSize)),
    Section("yStrikeoutPosition", OTFInt16(yStrikeoutPosition)),
    Section("sFamilyClass", OTFInt16(sFamilyClass)),
    Section("panose", panose),
    Section("ulUnicodeRange", ulUnicodeRange),
    Section("tag", OTFString(tag)),
    Section("fsSelection", OTFUInt16(fsSelection)),
    Section("usFirstCharIndex", OTFUInt16(usFirstCharIndex)),
    Section("usLastCharIndex", OTFUInt16(usLastCharIndex)),
    Section("sTypoAscender", OTFInt16(sTypoAscender)),
    Section("sTypoDescender", OTFInt16(sTypoDescender)),
    Section("sTypoLineGap", OTFInt16(sTypoLineGap)),
    Section("usWinAscent", OTFUInt16(usWinAscent)),
    Section("usWinDescent", OTFUInt16(usWinDescent)),
    Section("ulCodePageRange", ulCodePageRange),
    Section("sxHeight", OTFInt16(sxHeight)),
    Section("sCapHeight", OTFInt16(sCapHeight)),
    Section("usDefaultChar", OTFUInt16(usDefaultChar)),
    Section("usBreakChar", OTFUInt16(usBreakChar)),
    Section("usMaxContext", OTFUInt16(usMaxContext)),
    Section("usLowerOpticalPointSize", OTFUInt16(usLowerOpticalPointSize)),
    Section("usUpperOpticalPointSize", OTFUInt16(usUpperOpticalPointSize))
  )

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator): UInt = UInt(98)

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator): Traversable[Data] = Seq()

}
