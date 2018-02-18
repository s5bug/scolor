package com.tsunderebug.scolor.otf.tables.color.svg

import com.tsunderebug.scolor.otf.tables.OpenTypeTable
import com.tsunderebug.scolor.otf.types.OTFOffset32
import com.tsunderebug.scolor.otf.types.num.{OTFUInt16, OTFUInt32}
import com.tsunderebug.scolor.table.Section
import com.tsunderebug.scolor.{ByteAllocator, Data}
import spire.math.{UInt, UShort}

case class OTFSVGTable(
                        documentIndex: OTFSVGDocumentIndex
                      ) extends OpenTypeTable {

  /**
    * @return the table tag/name/identifier
    */
  override def name: String = "SVG "

  /**
    * @param b The byte allocator
    * @return the sections/partitions/rows of a table
    */
  override def sections(b: ByteAllocator): Traversable[Section] = Seq(
    Section("version", OTFUInt16(UShort(0))),
    Section("svgDocIndexOffset", OTFOffset32({
      val myOffset = b.allocate(this)
      val indOffset = b.allocate(documentIndex)
      val l = (indOffset.position - myOffset.position).toLong
      println(s"svto: $l")
      l
    })),
    Section("reserved", OTFUInt32(UInt(0)))
  )

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator): UInt = UInt(10)

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator): Traversable[Data] = Seq(documentIndex)

}
