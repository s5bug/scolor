package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.otf.tables.OTFNAMETable
import com.tsunderebug.scolor.otf.types.gen.{MacLanguage, WindowsLanguage}
import com.tsunderebug.scolor.table._
import com.tsunderebug.scolor.{ByteAllocator, Data}
import spire.math.{UInt, UShort}
import spire.syntax.std.array._

private[scolor] class TabledNameRecord(
                                        platformID: UShort,
                                        encodingID: UShort,
                                        languageID: UShort,
                                        nameID: UShort,
                                        val data: OTFString,
                                        table: OTFNAMETable
                                      ) extends EnclosingSectionDataType {

  override def sections(b: ByteAllocator): Traversable[Section] = Seq(
    Section("platformID", OTFUInt16(platformID)),
    Section("encodingID", OTFUInt16(encodingID)),
    Section("languageID", OTFUInt16(languageID)),
    Section("nameID", OTFUInt16(nameID)),
    Section("length", OTFUInt16(UShort(data.length(b).toShort))),
    Section("offset", OTFOffset16((b.allocate(data).position - b.allocate(table).position).toInt + 5 + table.records.map(_.apply(table).length(b)).toArray.qsum.toInt))
  )

  override def length(b: ByteAllocator): UInt = UInt(12)

  /**
    * Gets data sections if this data block has offsets
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator): Traversable[Data] = Seq(data)

}

case class OTFNameRecord(
                          platformID: UShort,
                          encodingID: UShort,
                          languageID: UShort,
                          nameID: UShort,
                          data: OTFString
                        ) extends RequireTable[OTFNAMETable, TabledNameRecord] {

  override def apply(t: OTFNAMETable) = new TabledNameRecord(platformID, encodingID, languageID, nameID, data, t)

}

object OTFNameRecord {

  def apply(nameID: UShort = UShort(4), d: WindowsLanguage.Dialect, content: OTFString): OTFNameRecord = OTFNameRecord(UShort(3), UShort(1), d.i, nameID, content)

  // This is a workaround for scala not liking default overloads :/
  def apply(script: UShort, language: UShort, content: OTFString): OTFNameRecord = OTFNameRecord(script, language, UShort(4), content)

  def apply(nameID: UShort, script: UShort, language: UShort, content: OTFString): OTFNameRecord = OTFNameRecord(UShort(1), script, language, nameID, content)

  def apply(d: MacLanguage, content: OTFString): OTFNameRecord = OTFNameRecord(d.script, d.language, UShort(4), content)

  def apply(nameID: UShort, d: MacLanguage, content: OTFString): OTFNameRecord = OTFNameRecord(nameID, d.script, d.language, UShort(4), content)

}
