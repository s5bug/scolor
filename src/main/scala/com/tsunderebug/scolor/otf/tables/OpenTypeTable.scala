package com.tsunderebug.scolor.otf.tables

import com.tsunderebug.scolor.{Font, Offset}
import com.tsunderebug.scolor.table.Table
import spire.math.UByte

abstract class OpenTypeTable extends Table {

  override def getBytes(f: Font): Array[UByte] = sections(f).flatMap(_.getBytes(f)).toArray
  def getPosition(f: Font): Offset = f.allocate(this)

}
