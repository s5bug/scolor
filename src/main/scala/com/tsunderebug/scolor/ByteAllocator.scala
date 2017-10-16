package com.tsunderebug.scolor

import spire.math.{UByte, UInt}

abstract class ByteAllocator {

  def insert(offset: Offset, data: Data): Unit
  def insert(data: Data): Unit = insert(nextOffset, data)
  def allocate(numBytes: UInt): Offset
  def allocate(data: Data, numBytes: UInt): Offset
  def allocate(data: Data): Offset = allocate(data, data.length(this))
  def nextOffset: Offset
  def getBytes: Array[UByte]

}
