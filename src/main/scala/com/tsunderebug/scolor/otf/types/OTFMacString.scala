package com.tsunderebug.scolor.otf.types

import com.tsunderebug.scolor.ByteAllocator
import spire.math.{UByte, UInt}

class OTFMacString(s: String) extends OTFString(s) {

  override def length(b: ByteAllocator): UInt = UInt(s.getBytes("UTF-8").length)

  override def bytes(b: ByteAllocator): Array[UByte] = s.getBytes("UTF-8").map(UByte(_))

}
