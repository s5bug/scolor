package com.tsunderebug.scolor

import spire.math.UByte

trait Font {

  def queueBytes(o: Offset, b: Array[UByte])
  def nextAvailableOffset(data: Data): Offset
  def getBytes: Array[Byte]

}
