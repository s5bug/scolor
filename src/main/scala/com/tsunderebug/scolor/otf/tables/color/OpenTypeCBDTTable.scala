package com.tsunderebug.scolor.otf.tables.color

import java.awt.image.BufferedImage

import com.tsunderebug.scolor.ByteAllocator
import com.tsunderebug.scolor.otf.tables.OpenTypeTable

class OpenTypeCBDTTable(
                       bitmaps: Seq[BufferedImage]
                       ) extends OpenTypeTable {

  override def name = "cbdt"

  override def sections(b: ByteAllocator) = Seq(

  )

  override def length(b: ByteAllocator) = ???

  /**
    * Gets data sections if this data block has offsets
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def getData(b: ByteAllocator) = Seq()

}
