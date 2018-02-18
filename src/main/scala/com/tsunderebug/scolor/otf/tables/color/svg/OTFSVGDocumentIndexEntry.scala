package com.tsunderebug.scolor.otf.tables.color.svg

import com.tsunderebug.scolor.otf.types.OTFOffset32
import com.tsunderebug.scolor.{ByteAllocator, Data}
import com.tsunderebug.scolor.otf.types.num.{OTFUInt16, OTFUInt32}
import com.tsunderebug.scolor.table.{EnclosingSectionDataType, RequireEnclosingSectionDataType, Section}
import spire.math.UInt

private case class DatadOTFSVGDocumentIndexEntry(
                                          documentIndex: OTFSVGDocumentIndex,
                                          startGlyphID: OTFUInt16,
                                          endGlyphID: OTFUInt16,
                                          svgDocument: OTFSVGDocument
                                   ) extends EnclosingSectionDataType {

  override def sections(b: ByteAllocator): Traversable[Section] = Seq(
    Section("startGlyphID", startGlyphID),
    Section("endGlyphID", endGlyphID),
    Section("svgDocOffset", OTFOffset32({
      val documentIndexOffset = b.allocate(documentIndex)
      val svgDocumentOffset = b.allocate(svgDocument)
      (svgDocumentOffset.position - documentIndexOffset.position).toLong
    })),
    Section("svgDocLength", OTFUInt32(svgDocument.length(b)))
  )

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator): UInt = UInt(8) + svgDocument.length(b)

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator): Traversable[Data] = Seq(svgDocument)

}

case class OTFSVGDocumentIndexEntry(
                                     startGlyphID: OTFUInt16,
                                     endGlyphID: OTFUInt16,
                                     svgDocument: OTFSVGDocument
                                   ) extends RequireEnclosingSectionDataType[OTFSVGDocumentIndex, DatadOTFSVGDocumentIndexEntry] {

  override def apply(t: OTFSVGDocumentIndex): DatadOTFSVGDocumentIndexEntry = {
    DatadOTFSVGDocumentIndexEntry(t, startGlyphID, endGlyphID, svgDocument)
  }

}
