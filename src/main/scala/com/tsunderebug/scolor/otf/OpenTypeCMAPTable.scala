package com.tsunderebug.scolor.otf

import com.tsunderebug.scolor.Font
import com.tsunderebug.scolor.otf.types.UInt16
import com.tsunderebug.scolor.table.{Section, Table}
import spire.math.{UByte, UShort}

class OpenTypeCMAPTable extends Table {

  override def name = "cmap"

  override def sections: Seq[Section] = Seq(
    Section("version", UInt16(UShort(0))),
    Section("numTables", UInt16(UShort(0))) // TODO CMAP encoding class
  )

  /**
    * The bytes of table data. The data does not have to be padded.
    *
    * @return Table data as a byte array.
    */
  override def getBytes(f: Font): Array[UByte] = sections.flatMap(_.getBytes(f)).toArray

}
