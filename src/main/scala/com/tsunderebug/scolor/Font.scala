package com.tsunderebug.scolor

import java.io.File

trait Font {

  def getBytes: Array[Byte]
  def writeFile(dir: File, name: String): Unit
  def nextAvailableOffset(d: Data): Offset

}
