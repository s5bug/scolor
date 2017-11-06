package com.tsunderebug.scolor.otf.tables

import com.tsunderebug.scolor.table.Table
import com.tsunderebug.scolor.{ByteAllocator, Offset}
import spire.math.UByte

abstract class OpenTypeTable extends Table {

  override def bytes(b: ByteAllocator): Array[UByte] = {
    sections(b).foldLeft(Array.empty[UByte]) {
      case (accum, section) => accum ++ section.getBytes(b)
    }
  }

  def position(b: ByteAllocator): Offset = b.allocate(this)

  override def alignment: UByte = UByte(4)

}
