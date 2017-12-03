package com.tsunderebug.scolor.otf.tables.os2

import com.tsunderebug.scolor.otf.types.OTFArray
import com.tsunderebug.scolor.otf.types.num.OTFUInt8
import com.tsunderebug.scolor.table.SectionDataType
import com.tsunderebug.scolor.{ByteAllocator, Data}
import spire.math.{UByte, UInt}

case class OTFPANOSEClassification(
                                    bFamilyType: UByte,
                                    bSerifStyle: UByte,
                                    bWeight: UByte,
                                    bProportion: UByte,
                                    bContrast: UByte,
                                    bStrokeVariation: UByte,
                                    bArmStyle: UByte,
                                    bLetterForm: UByte,
                                    bMidline: UByte,
                                    bXHeight: UByte
                                  ) extends SectionDataType {

  /**
    * @param b The byte allocator
    * @return an array of unsigned bytes representing the data.
    */
  override def bytes(b: ByteAllocator): Array[UByte] = OTFArray(Seq(bFamilyType, bSerifStyle, bWeight, bProportion, bContrast, bStrokeVariation, bArmStyle, bLetterForm, bMidline, bXHeight).map(OTFUInt8)).bytes(b)

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator): UInt = UInt(10)

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator): Traversable[Data] = Seq()

}
