package com.tsunderebug.scolor.otf.tables.color.svg

import com.tsunderebug.scolor.otf.types.OTFArray
import com.tsunderebug.scolor.otf.types.num.OTFUInt16
import com.tsunderebug.scolor.{ByteAllocator, Data}
import com.tsunderebug.scolor.table.{EnclosingSectionDataType, Section}
import spire.math.{UInt, UShort}

case class OTFSVGDocumentIndex(
                                entries: Traversable[OTFSVGDocumentIndexEntry]
                              ) extends EnclosingSectionDataType {

  private val mappedEntries = entries.map(_.apply(this))

  override def sections(b: ByteAllocator): Traversable[Section] = Seq(
    Section("numEntries", OTFUInt16(UShort(mappedEntries.size))),
    Section("entries", OTFArray(mappedEntries))
  )

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator): UInt = UInt(2) + UInt(mappedEntries.map(_.length(b).toLong).sum)

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator): Traversable[Data] = {
    println("did")
    Seq()
  }

}
