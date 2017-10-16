package com.tsunderebug.scolor.otf

import com.tsunderebug.scolor.otf.types.Offset32
import com.tsunderebug.scolor.{ByteAllocator, Data, Offset}
import spire.math.{UByte, UInt}

import scala.collection.mutable

class OTFByteAllocator(f: OpenTypeFont) extends ByteAllocator {

  private val allocMap: mutable.Map[Data, Offset] = mutable.Map()
  private val byteMap: mutable.Map[Offset, UByte] = mutable.Map()
  private var nextAvailableOffset: Offset = Offset32(0)

  def insert(offset: Offset, data: Data): Unit = {
    val bytes = data.getBytes(this)
    val top = offset.position.toInt + bytes.length
    (offset.position.toInt until top).foreach((p) => byteMap += (Offset32(p) -> bytes(p - offset.position.toInt)))
    nextAvailableOffset = Offset32(top + (4 - (top % 4)))
    data.getData(this).foreach(insert)
  }

  def allocate(numBytes: UInt): Offset = {
    val prev = nextOffset
    nextAvailableOffset = Offset32((prev.position + numBytes).toLong)
    prev
  }

  def allocate(data: Data, numBytes: UInt): Offset = {
    allocMap.getOrElseUpdate(data, allocate(numBytes))
  }

  def nextOffset: Offset = nextAvailableOffset

  def getBytes: Array[UByte] = {
    val m: Int = byteMap.map((t) => t._1.position.toInt).max
    (0 to m).map((i) => byteMap.getOrElse(Offset32(i), UByte(0))).toArray
  }

}
