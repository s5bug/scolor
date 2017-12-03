package com.tsunderebug.scolor.otf.tables.os2

import com.tsunderebug.scolor.table.SectionDataType
import com.tsunderebug.scolor.{ByteAllocator, Data}
import spire.math.{UByte, UInt}

object OTFCodePageRange {

  class OTFCodePageRangeFlag extends SectionDataType {

    def |(other: OTFCodePageRangeFlag): OTFCodePageRangeFlag = new OTFCodePageRangeFlag {
      override def flag: BigInt = OTFCodePageRangeFlag.this.flag | other.flag
    }

    def flag: BigInt = BigInt(0)

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
    override def length(b: ByteAllocator): UInt = UInt(8)

    /**
      * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
      *
      * @param b The byte allocator
      * @return an array of Data objects
      */
    override def data(b: ByteAllocator): Traversable[Data] = Seq()

  }

  case object `Latin 1` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 0
  }

  case object `Latin 2: Eastern Europe` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 1
  }

  case object `Cyrillic` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 2
  }

  case object `Greek` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 3
  }

  case object `Turkish` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 4
  }

  case object `Hebrew` extends OTFCodePageRangeFlag {
    override def flag: BigInt = (BigInt(1) << 5) | (BigInt(1) << 53)
  }

  case object `Arabic` extends OTFCodePageRangeFlag {
    override def flag: BigInt = (BigInt(1) << 6) | (BigInt(1) << 51)
  }

  case object `Windows Baltic` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 7
  }

  case object `Vietnamese` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 8
  }

  case object `Thai` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 16
  }

  case object `JIS/Japan` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 17
  }

  case object `Chinese: Simplified chars--PRC and Singapore` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 18
  }

  case object `Korean Wansung` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 19
  }

  case object `Chinese: Traditional chars--Taiwan and Hong Kong` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 20
  }

  case object `Korean Johab` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 21
  }

  case object `Macintosh Character Set (US Roman)` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 29
  }

  case object `OEM Character Set` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 30
  }

  case object `Symbol Character Set` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 31
  }

  case object `IBM Greek` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 48
  }

  case object `MS-DOS Russian` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 49
  }

  case object `MS-DOS Nordic` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 50
  }

  case object `MS-DOS Canadian French` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 52
  }

  case object `MS-DOS Icelandic` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 54
  }

  case object `MS-DOS Portuguese` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 55
  }

  case object `IBM Turkish` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 56
  }

  case object `IBM Cyrillic; primarily Russian` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 57
  }

  case object `Latin 2` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 58
  }

  case object `MS-DOS Baltic` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 59
  }

  case object `Greek; former 437 G` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 60
  }

  case object `Arabic; ASMO 708` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 61
  }

  case object `WE/Latin 1` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 62
  }

  case object `US` extends OTFCodePageRangeFlag {
    override def flag: BigInt = BigInt(1) << 63
  }

}
