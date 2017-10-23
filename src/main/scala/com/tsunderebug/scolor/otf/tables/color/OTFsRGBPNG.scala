package com.tsunderebug.scolor.otf.tables.color

import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

import com.tsunderebug.scolor.ByteAllocator
import com.tsunderebug.scolor.table.SectionDataType
import spire.math.{UByte, UInt}

case class OTFsRGBPNG(i: BufferedImage) extends SectionDataType {

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    *
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  override def length(b: ByteAllocator) = UInt(bytes(b).length)

  /**
    * @param b The byte allocator
    * @return an array of unsigned bytes representing the data.
    */
  override def bytes(b: ByteAllocator): Array[UByte] = {
    val baos = new ByteArrayOutputStream()
    ImageIO.write(i, "png", baos)
    baos.close()
    baos.toByteArray.map(UByte(_))
  }

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    *
    * @param b The byte allocator
    * @return an array of Data objects
    */
  override def data(b: ByteAllocator) = Seq()

}
