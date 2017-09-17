package com.tsunderebug.scolor.otf

import com.tsunderebug.scolor.Font
import com.tsunderebug.scolor.otf.types.{EncodingRecord, UInt16}
import com.tsunderebug.scolor.table.{Section, Table}
import spire.math.{UByte, UInt, UShort}

import scala.collection.mutable

class OpenTypeCMAPTable extends Table {

  private var encodingRecords: mutable.Seq[EncodingRecord] = mutable.Seq()

  override def name = "cmap"

  override def sections: Seq[Section] = Seq(
    Section("version", UInt16(UShort(0))),
    Section("numTables", UInt16(UShort(encodingRecords.length))) // TODO CMAP encoding class
  )

  /**
    * The bytes of table data. The data does not have to be padded.
    *
    * @return Table data as a byte array.
    */
  override def getBytes(f: Font): Array[UByte] = sections.flatMap(_.getBytes(f)).toArray

  override def length: UInt = {
    UInt(sections.map(_.data.length.toLong).sum)
  }

}
