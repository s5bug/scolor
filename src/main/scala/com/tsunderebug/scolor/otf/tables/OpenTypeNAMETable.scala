package com.tsunderebug.scolor.otf.tables

import com.tsunderebug.scolor.otf.types._
import com.tsunderebug.scolor.table.Section
import com.tsunderebug.scolor.{ByteAllocator, Data}
import spire.math.{UInt, UShort}
import spire.syntax.std.array._

case class OpenTypeNAMETable(
                            records: Seq[NameRecord]
                            ) extends OpenTypeTable {

  override def name = "name"

  val strData = OTFArray(records.map((r) => OTFString(r.data.s)))
  private val tabeledRecords = records.map(_(this))

  override def sections(b: ByteAllocator): Seq[Section] = {
    val off = b.allocate(this)
    val tabeledRecordsSum = tabeledRecords.foldLeft(UInt(0)) {
      case (accum, tabledRecord) => accum + tabledRecord.length(b)
    }
    val dataOff = off.position + UInt(6) + tabeledRecordsSum
    Seq(
      Section("format", UInt16(UShort(0))),
      Section("count", UInt16(UShort(records.length))),
      Section("stringOffset", Offset16(6 + tabeledRecordsSum.toInt)),
      Section("nameRecords", OTFArray(tabeledRecords)),
      Section("data", strData)
    )
  }

  override def length(b: ByteAllocator): UInt = {
    records.foldLeft(UInt(6) + strData.length(b)) { // Start with 6 because ???
      case (accum, record) => accum + record(this).length(b)
    }
  }

  /**
    * Gets data sections if this data block has offsets
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def getData(b: ByteAllocator): Seq[Data] = Seq()

}