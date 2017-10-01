package com.tsunderebug.scolor

import spire.math.{UByte, UInt}

trait Data {

  def length: UInt
  def getBytes(f: Font): Array[UByte]

  /**
    * Gets data sections if this data block has offsets
    * @param f The font
    * @return an array of Data objects
    */
  def data(f: Font): Array[Data]

}
