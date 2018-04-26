package com.tsunderebug.scolor.otf.tables.os2

import com.tsunderebug.scolor.table.SectionDataType
import com.tsunderebug.scolor.{ByteAllocator, Data}
import spire.math.{UByte, UInt}

object OTFUnicodeRange {

  class OTFUnicodeRangeFlag extends SectionDataType {

    def |(other: OTFUnicodeRangeFlag): OTFUnicodeRangeFlag = new OTFUnicodeRangeFlag {
      override def flag: BigInt = OTFUnicodeRangeFlag.this.flag | other.flag
    }

    def flag: BigInt = 0

    /**
      * @param b The byte allocator
      * @return an array of unsigned bytes representing the data.
      */
    override def bytes(b: ByteAllocator): Array[UByte] = (for (i <- 0 until length(b).signed) yield flag.testBit(i)).reverse.grouped(8).toArray.reverse.map(_.reverse).map((bs) => {
      var flag: UByte = UByte(0)
      bs.zipWithIndex.filter(_._1).foreach { case (_, bit) => flag = flag | UByte(1 << bit) }
      flag
    })

    /**
      * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
      *
      * @param b The byte allocator
      * @return an unsigned integer describing the length of this data block
      */
    override def length(b: ByteAllocator): UInt = UInt(16)

    /**
      * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
      *
      * @param b The byte allocator
      * @return an array of Data objects
      */
    override def data(b: ByteAllocator): Traversable[Data] = Seq()

  }

  case object `Basic Latin` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 0
  }

  case object `Latin-1 Supplement` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 1
  }

  case object `Latin Extended-A` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 2
  }

  case object `Latin Extended-B` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 3
  }

  case object `IPA Extensions` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 4
  }

  case object `Spacing Modifier Letters` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 5
  }

  case object `Combining Diacritical Marks` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 6
  }

  case object `Greek and Coptic` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 7
  }

  case object `Coptic` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 8
  }

  case object `Cyrillic` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 9
  }

  case object `Armenian` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 10
  }

  case object `Hebrew` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 11
  }

  case object `Vai` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 12
  }

  case object `Arabic` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 13
  }

  case object `NKo` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 14
  }

  case object `Devanagari` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 15
  }

  case object `Bengali` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 16
  }

  case object `Gurmukhi` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 17
  }

  case object `Gujarati` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 18
  }

  case object `Oriya` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 19
  }

  case object `Tamil` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 20
  }

  case object `Telugu` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 21
  }

  case object `Kannada` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 22
  }

  case object `Malayalam` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 23
  }

  case object `Thai` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 24
  }

  case object `Lao` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 25
  }

  case object `Georgian` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 26
  }

  case object `Balinese` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 27
  }

  case object `Hangul Jamo` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 28
  }

  case object `Latin Extended Additional` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 29
  }

  case object `Greek Extended` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 30
  }

  case object `General Punctuation` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 31
  }

  case object `Superscripts And Subscripts` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 32
  }

  case object `Currency Symbols` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 33
  }

  case object `Combining Diacritical Marks For Symbols` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 34
  }

  case object `Letterlike Symbols` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 35
  }

  case object `Number Forms` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 36
  }

  case object `Arrows` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 37
  }

  case object `Mathematical Operators` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 38
  }

  case object `Miscellaneous Technical` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 39
  }

  case object `Control Pictures` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 40
  }

  case object `Optical Character Recognition` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 41
  }

  case object `Enclosed Alphanumerics` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 42
  }

  case object `Box Drawing` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 43
  }

  case object `Block Elements` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 44
  }

  case object `Geometric Shapes` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 45
  }

  case object `Miscellaneous Symbols` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 46
  }

  case object `Dingbats` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 47
  }

  case object `CJK Symbols And Punctuation` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 48
  }

  case object `Hiragana` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 49
  }

  case object `Katakana` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 50
  }

  case object `Bopomofo` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 51
  }

  case object `Hangul Compatibility Jamo` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 52
  }

  case object `Phags-pa` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 53
  }

  case object `Enclosed CJK Letters And Months` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 54
  }

  case object `CJK Compatibility` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 55
  }

  case object `Hangul Syllables` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 56
  }

  case object `Non-Plane 0 *` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 57
  }

  case object `Phoenician` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 58
  }

  case object `CJK Unified Ideographs` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 59
  }

  case object `Private Use Area (plane 0)` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 60
  }

  case object `CJK Strokes` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 61
  }

  case object `Alphabetic Presentation Forms` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 62
  }

  case object `Arabic Presentation Forms-A` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 63
  }

  case object `Combining Half Marks` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 64
  }

  case object `Vertical Forms` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 65
  }

  case object `Small Form Variants` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 66
  }

  case object `Arabic Presentation Forms-B` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 67
  }

  case object `Halfwidth And Fullwidth Forms` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 68
  }

  case object `Specials` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 69
  }

  case object `Tibetan` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 70
  }

  case object `Syriac` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 71
  }

  case object `Thaana` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 72
  }

  case object `Sinhala` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 73
  }

  case object `Myanmar` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 74
  }

  case object `Ethiopic` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 75
  }

  case object `Cherokee` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 76
  }

  case object `Unified Canadian Aboriginal Syllabics` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 77
  }

  case object `Ogham` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 78
  }

  case object `Runic` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 79
  }

  case object `Khmer` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 80
  }

  case object `Mongolian` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 81
  }

  case object `Braille Patterns` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 82
  }

  case object `Yi Syllables` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 83
  }

  case object `Tagalog` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 84
  }

  case object `Old Italic` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 85
  }

  case object `Gothic` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 86
  }

  case object `Deseret` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 87
  }

  case object `Byzantine Musical Symbols` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 88
  }

  case object `Mathematical Alphanumeric Symbols` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 89
  }

  case object `Private Use (plane 15)` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 90
  }

  case object `Variation Selectors` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 91
  }

  case object `Tags` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 92
  }

  case object `Limbu` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 93
  }

  case object `Tai Le` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 94
  }

  case object `New Tai Lue` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 95
  }

  case object `Buginese` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 96
  }

  case object `Glagolitic` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 97
  }

  case object `Tifinagh` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 98
  }

  case object `Yijing Hexagram Symbols` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 99
  }

  case object `Syloti Nagri` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 100
  }

  case object `Linear B Syllabary` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 101
  }

  case object `Ancient Greek Numbers` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 102
  }

  case object `Ugaritic` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 103
  }

  case object `Old Persian` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 104
  }

  case object `Shavian` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 105
  }

  case object `Osmanya` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 106
  }

  case object `Cypriot Syllabary` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 107
  }

  case object `Kharoshthi` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 108
  }

  case object `Tai Xuan Jing Symbols` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 109
  }

  case object `Cuneiform` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 110
  }

  case object `Counting Rod Numerals` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 111
  }

  case object `Sundanese` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 112
  }

  case object `Lepcha` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 113
  }

  case object `Ol Chiki` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 114
  }

  case object `Saurashtra` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 115
  }

  case object `Kayah Li` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 116
  }

  case object `Rejang` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 117
  }

  case object `Cham` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 118
  }

  case object `Ancient Symbols` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 119
  }

  case object `Phaistos Disc` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 120
  }

  case object `Carian` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 121
  }

  case object `Domino Tiles` extends OTFUnicodeRangeFlag {
    override def flag: BigInt = BigInt(1) << 122
  }

}
