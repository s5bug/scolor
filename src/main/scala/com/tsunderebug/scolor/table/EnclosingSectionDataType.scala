package com.tsunderebug.scolor.table

import com.tsunderebug.scolor.ByteAllocator
import spire.math.UByte

/**
  * This is a table but without tags or etc. Used for when you need a custom data type in a table.
  */
abstract class EnclosingSectionDataType extends SectionDataType {

  def sections(b: ByteAllocator): Traversable[Section]

  override def bytes(b: ByteAllocator): Array[UByte] = sections(b).flatMap(_.bytes(b)).toArray

}
