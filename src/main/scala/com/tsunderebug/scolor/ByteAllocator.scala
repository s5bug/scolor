package com.tsunderebug.scolor

import spire.math.{UByte, UInt}

abstract class ByteAllocator {

  /**
    * Insert data at an offset in the byte allocator.
    *
    * @param offset The offset
    * @param data   The data
    */
  def insert(offset: Offset, data: Data): Unit

  /**
    * Insert data at the next available offset.
    *
    * @param data The data
    */
  def insert(data: Data): Unit = insert(allocate(data), data)

  /**
    * Allocate an offset for a number of bytes.
    *
    * @param numBytes The number of bytes to allocate
    * @return an offset describing the next open space these bytes can fit
    */
  def allocate(numBytes: UInt): Offset

  def allocate(data: Data, numBytes: UInt): Offset

  def allocate(data: Data): Offset = allocate(data, data.length(this))

  def nextOffset: Offset

  def getBytes: Array[UByte]

}
