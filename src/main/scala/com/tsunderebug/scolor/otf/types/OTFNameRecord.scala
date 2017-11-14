package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.otf.tables.OTFNAMETable
import com.tsunderebug.scolor.otf.types.gen.{MacLanguage, WindowsLanguage}
import com.tsunderebug.scolor.otf.types.num.OTFUInt16
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
                          nameID: NameID,
                          data: OTFString
                        ) extends RequireTable[OTFNAMETable, TabledNameRecord] {

  override def apply(t: OTFNAMETable) = new TabledNameRecord(platformID, encodingID, languageID, nameID.id, data, t)

}

object OTFNameRecord {

  def apply(nameID: NameID = FullName, d: WindowsLanguage.Dialect, content: String): OTFNameRecord = OTFNameRecord(UShort(3), UShort(1), d.i, nameID, OTFString(content))

  // This is a workaround for scala not liking default overloads :/
  def apply(script: UShort, language: UShort, content: String, macString: Boolean): OTFNameRecord = OTFNameRecord(FullName, script, language, content, macString)

  def apply(d: MacLanguage, content: String): OTFNameRecord = OTFNameRecord(FullName, d.script, d.language, content, macString = true)

  def apply(nameID: NameID, d: MacLanguage, content: String): OTFNameRecord = OTFNameRecord(nameID, d.script, d.language, content, macString = true)

  def apply(nameID: NameID, script: UShort, language: UShort, content: String, macString: Boolean): OTFNameRecord = OTFNameRecord(UShort(1), script, language, nameID, if (macString) new OTFMacString(content) else OTFString(content))

}

sealed trait NameID {
  def id: UShort
}

case object CopyrightNotice extends NameID {
  override def id = UShort(0)
}

case object Family extends NameID {
  override def id = UShort(1)
}

case object Style extends NameID {
  override def id = UShort(2)
}

case object UniqueIdentifier extends NameID {
  override def id = UShort(3)
}

case object FullName extends NameID {
  override def id = UShort(4)
}

case object Version extends NameID {
  override def id = UShort(5)
}

case object PostScriptName extends NameID {
  override def id = UShort(6)
}

case object Trademark extends NameID {
  override def id = UShort(7)
}

case object Manufacturer extends NameID {
  override def id = UShort(8)
}

case object Designer extends NameID {
  override def id = UShort(9)
}

case object Description extends NameID {
  override def id = UShort(10)
}

case object VendorURL extends NameID {
  override def id = UShort(11)
}

case object DesignerURL extends NameID {
  override def id = UShort(12)
}

case object License extends NameID {
  override def id = UShort(13)
}

case object LicenseURL extends NameID {
  override def id = UShort(14)
}

case object TypographicFamily extends NameID {
  override def id = UShort(16)
}

case object TypographicSubfamily extends NameID {
  override def id = UShort(17)
}

case object MacOSMenuName extends NameID {
  override def id = UShort(18)
}

case object SampleDisplayText extends NameID {
  override def id = UShort(19)
}

case object PostScriptFindFontName extends NameID {
  override def id = UShort(20)
}

case object WWSFamilyName extends NameID {
  override def id = UShort(21)
}

case object WWSSubfamilyName extends NameID {
  override def id = UShort(22)
}

case object LightBackgroundPalette extends NameID {
  override def id = UShort(23)
}

case object DarkBackgroundPalette extends NameID {
  override def id = UShort(24)
}

case object PostscriptVariationsPrefix extends NameID {
  override def id = UShort(25)
}