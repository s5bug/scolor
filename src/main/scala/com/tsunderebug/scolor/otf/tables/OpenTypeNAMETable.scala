package com.tsunderebug.scolor.otf.tables

import com.tsunderebug.scolor.otf.types._
import com.tsunderebug.scolor.table.Section
import com.tsunderebug.scolor.{Data, Font}
import spire.math.{UInt, UShort}
import spire.syntax.std.array._

case class OpenTypeNAMETable(
                            records: Seq[NameRecord]
                            ) extends OpenTypeTable {

  override def name = "name"

  val strData = OTFString(records.map(_.data.s).mkString)
  private val tabeledRecords = records.map(_(this))

  override def sections(f: Font): Seq[Section] = {
    val off = f.allocate(this)
    val dataOff = off.position + UInt(6) + tabeledRecords.map(_.length(f)).toArray.qsum
    Seq(
      Section("format", UInt16(UShort(0))),
      Section("count", UInt16(UShort(records.length))),
      Section("stringOffset", Offset16(6 + tabeledRecords.map(_.length(f)).toArray.qsum.toInt)),
      Section("nameRecords", OTFArray(tabeledRecords)),
      Section("data", strData)
    )
  }

  override def length(f: Font): UInt = UInt(6) + records.map(_(this).length(f)).toArray.qsum + strData.length(f)

  /**
    * Gets data sections if this data block has offsets
    *
    * @param f The font
    * @return an array of Data objects
    */
  override def getData(f: Font): Array[Data] = Array()

}
