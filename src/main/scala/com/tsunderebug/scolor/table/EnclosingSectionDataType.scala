package com.tsunderebug.scolor.table

import com.tsunderebug.scolor.{ByteAllocator, Font}
import spire.math.UByte

abstract class EnclosingSectionDataType extends SectionDataType {

  def sections(b: ByteAllocator): Seq[Section]

  override def getBytes(b: ByteAllocator): Array[UByte] = sections(b).flatMap(_.getBytes(b)).toArray

}
