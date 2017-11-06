package com.tsunderebug.scolor.otf.tables.color.google

import com.tsunderebug.scolor.ByteAllocator
import com.tsunderebug.scolor.otf.tables.OpenTypeTable
import com.tsunderebug.scolor.otf.types._
import com.tsunderebug.scolor.table.Section
import spire.math.{UInt, UShort}
import spire.syntax.std.array._

case class OTFCBDTTable(
                         gds: Traversable[OTFGoogleGlyphData]
                  ) extends OpenTypeTable {

  override def name = "CBDT"

  override def sections(b: ByteAllocator) = Seq(
    Section("majorVersion", OTFUInt16(UShort(3))),
    Section("minorVersion", OTFUInt16(UShort(0))),
    Section("data", OTFArray(gds))
  )

  override def length(b: ByteAllocator): UInt = UInt(4) + gds.map(_.length(b)).toArray.qsum

  /**
    * Gets data sections if this data block has offsets
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator) = Seq()

}
