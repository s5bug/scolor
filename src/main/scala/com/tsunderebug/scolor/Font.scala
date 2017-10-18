package com.tsunderebug.scolor

import java.io.File

import spire.math.UByte

/**
  * This supports having different styles or weights of fonts.
  */
trait Font {

  /**
    * @return The bytes of the font file
    */
  def getBytes: Array[UByte]

  /**
    * Write a font/set of fonts to a directory. Multiple are allowed for different platforms or styles.
    *
    * @param dir  The directory
    * @param name The base name of the font
    */
  def writeFile(dir: File, name: String): Unit

}
