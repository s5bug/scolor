package com.tsunderebug.scolor.otf.tables.color.svg

import com.tsunderebug.scolor.StringableDocument
import com.tsunderebug.scolor.{ByteAllocator, Data}
import org.w3c.dom.Document
import spire.math.{UByte, UInt}

case class OTFSVGDocument(
                           svg: Document
                         ) extends Data {

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator): UInt = UInt(svg.toXmlString.length)

  /**
    * Get the bytes to insert at the offset the byte allocator gives you.
    *
    * @param b The byte allocator
    * @return an array of unsigned bytes representing the font data.
    */
  override def bytes(b: ByteAllocator): Array[UByte] = {
    val bs = svg.toXmlString.getBytes("UTF-8").map(UByte(_))
    println(bs.grouped(16).map((r) => r.map(_.toLong.toHexString.reverse.padTo(2, '0').reverse).mkString(" ")).mkString("\n"))
    bs
  }

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator): Traversable[Data] = Seq()

}
