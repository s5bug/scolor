package com.tsunderebug.scolor

import spire.math.{UByte, UInt}

trait Data {

  /**
    * Calculate/retrieve/return length in bytes of this data. Useful for if data needs to be allocated before it is calculated.
    * @param b The byte allocator
    * @return an unsigned integer describing the length of this data block
    */
  def length(b: ByteAllocator): UInt

  /**
    * Get the bytes to insert at the offset the byte allocator gives you.
    * @param b The byte allocator
    * @return an array of unsigned bytes representing the font data.
    */
  def getBytes(b: ByteAllocator): Array[UByte]

  /**
    * Gets data sections if this data block has offsets. Used for if data needs to be allocated but can be in any location.
    * @param b The byte allocator
    * @return an array of Data objects
    */
  def getData(b: ByteAllocator): Seq[Data]

}
