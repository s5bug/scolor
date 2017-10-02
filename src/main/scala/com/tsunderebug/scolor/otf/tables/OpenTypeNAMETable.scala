package com.tsunderebug.scolor.otf.tables

import com.tsunderebug.scolor.{Data, Font}
import com.tsunderebug.scolor.otf.types.UInt16
import com.tsunderebug.scolor.table.{Section, Table}
import spire.math.UShort

class OpenTypeNAMETable extends Table {

  override def name = "name"

  override def sections = Seq(
    Section("format", UInt16(UShort(0)))
  )

  override def getBytes(f: Font) = ???

  override def length = ???

  /**
    * Gets data sections if this data block has offsets
    *
    * @param f The font
    * @return an array of Data objects
    */
  override def getData(f: Font): Array[Data] = Array()

}
