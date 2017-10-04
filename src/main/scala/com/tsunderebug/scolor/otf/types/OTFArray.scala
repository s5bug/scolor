package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.table.SectionDataType
import com.tsunderebug.scolor.{Data, Font}
import spire.math.{UByte, UInt}
import spire.syntax.std.array._

case class OTFArray[T <: Data](elems: Seq[T]) extends SectionDataType {

  override def length(f: Font): UInt = elems.map(_.length(f)).toArray.qsum

  override def getBytes(f: Font): Array[UByte] = elems.flatMap(_.getBytes(f)).toArray

  /**
    * Gets data sections if this data block has offsets
    *
    * @param f The font
    * @return an array of Data objects
    */
  override def getData(f: Font): Array[Data] = Array()

}
