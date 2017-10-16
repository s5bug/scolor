package com.tsunderebug.scolor

import spire.math.{UByte, UInt}

trait Data {

  def length(b: ByteAllocator): UInt
  def getBytes(b: ByteAllocator): Array[UByte]

  /**
    * Gets data sections if this data block has offsets
    * @param b The byte allocator
    * @return an array of Data objects
    */
  def getData(b: ByteAllocator): Seq[Data]

}
