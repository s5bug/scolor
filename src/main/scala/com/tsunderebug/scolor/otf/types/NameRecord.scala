package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.otf.tables.OpenTypeNAMETable
import com.tsunderebug.scolor.otf.types.gen.WindowsLanguage
import com.tsunderebug.scolor.table._
import com.tsunderebug.scolor.{Data, Font}
import spire.math.{UInt, UShort}
import spire.syntax.std.array._

private[scolor] class TabledNameRecord(
                                platformID: UShort,
                                encodingID: UShort,
                                languageID: UShort,
                                nameID: UShort,
                                val data: OTFString,
                                table: OpenTypeNAMETable
                              ) extends EnclosingSectionDataType {

  override def sections(f: Font): Seq[Section] = Seq(
    Section("platformID", UInt16(platformID)),
    Section("encodingID", UInt16(encodingID)),
    Section("languageID", UInt16(languageID)),
    Section("nameID", UInt16(nameID)),
    Section("length", UInt16(UShort(data.length(f).toShort))),
    Section("offset", Offset16((f.allocate(data).position - f.allocate(table).position).toInt + 5 + table.records.map(_.apply(table).length(f)).toArray.qsum.toInt))
  )

  override def length(f: Font): UInt = UInt(12)

  /**
    * Gets data sections if this data block has offsets
    *
    * @param f The font
    * @return an array of Data objects
    */
  override def getData(f: Font): Array[Data] = Array(data)

}

case class NameRecord(
                       platformID: UShort,
                       encodingID: UShort,
                       languageID: UShort,
                       nameID: UShort,
                       data: OTFString
                     ) extends RequireTable[OpenTypeNAMETable, TabledNameRecord] {

  override def apply(t: OpenTypeNAMETable) = new TabledNameRecord(platformID, encodingID, languageID, nameID, data, t)

}

object NameRecord {

  def apply(nameID: UShort = UShort(4), d: WindowsLanguage.Dialect, content: OTFString): NameRecord = NameRecord(UShort(3), UShort(1), d.i, nameID, content)

}
