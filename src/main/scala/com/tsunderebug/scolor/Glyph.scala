package com.tsunderebug.scolor

import java.awt.image.BufferedImage

import org.w3c.dom.svg.SVGDocument

/**
  * Holds glyph data of a font. Will draw as large as specified by the glyph data onto a buffered image.
  */
trait Glyph {

  /**
    * Used for drawing the glyph in places
    *
    * @return A raster of the glyph
    */
  def toBitmap: BufferedImage

  def toScalable: SVGDocument

}
