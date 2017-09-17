package com.tsunderebug.scolor

import spire.math.{UByte, UInt}

trait Data {

  def length: UInt
  def getBytes(f: Font): Array[UByte]

}
