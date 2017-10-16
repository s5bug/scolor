package com.tsunderebug.scolor

import java.io.File

import spire.math.UByte

trait Font {

  def getBytes: Array[UByte]
  def writeFile(dir: File, name: String): Unit

}
