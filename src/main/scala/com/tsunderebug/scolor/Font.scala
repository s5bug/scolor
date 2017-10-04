package com.tsunderebug.scolor

import java.io.File

import spire.math.{UByte, UInt}

abstract class Font {

  def getBytes: Array[UByte]
  protected[scolor] def insert(data: Data): Unit = {
    insert(allocate(data), data)
  }
  protected[scolor] def insert(offset: Offset, data: Data)
  protected[scolor] def allocate(data: Data): Offset
  protected[scolor] def allocate(data: Data, numBytes: UInt): Offset

  /**
    * This should cache.
    * @param numBytes The number of bytes to allocate
    * @return The starting offset of the data
    */
  protected[scolor] def allocate(numBytes: UInt): Offset
  def writeFile(dir: File, name: String): Unit
  protected[scolor] def nextOffset: Offset

}
