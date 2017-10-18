package com.tsunderebug.scolor

import java.awt.image.BufferedImage

/**
  * Holds glyph data of a font. Will draw as large as specified by the glyph data onto a buffered image.
  */
trait Glyph extends Data {

  /**
    * Used for drawing the glyph in places
    *
    * @return An image of the glyph
    */
  def toImage: BufferedImage

}
