package com.tsunderebug.scolor.otf

import com.tsunderebug.scolor.otf.types.OTFOffset32
import com.tsunderebug.scolor.{ByteAllocator, Data, Offset}
import spire.math.{UByte, UInt}

import scala.collection.mutable

class OTFByteAllocator(f: OpenTypeFont) extends ByteAllocator {

  private val allocMap: mutable.Map[Data, Offset] = mutable.Map()
  private val byteMap: mutable.Map[Offset, UByte] = mutable.Map()
  private var nextAvailableOffset: Offset = OTFOffset32(0)

  def insert(offset: Offset, data: Data): Unit = {
    val bytes = data.bytes(this)
    val top = offset.position.toInt + bytes.length
    (offset.position.toInt until top).foreach((p) => byteMap += (OTFOffset32(p) -> bytes(p - offset.position.toInt)))
    nextAvailableOffset = OTFOffset32(Math.max(top + (data.alignment.toInt - (top % data.alignment.toInt)), nextOffset.position.toLong))
    data.data(this).foreach(insert)
  }

  def nextOffset: Offset = nextAvailableOffset

  def allocate(data: Data, numBytes: UInt): Offset = {
    allocMap.getOrElseUpdate(data, allocate(numBytes))
  }

  def allocate(numBytes: UInt): Offset = {
    allocate(numBytes, UByte(1))
  }

  def allocate(numBytes: UInt, alignment: UByte): Offset = {
    val prev = nextOffset
    val unaligned = prev.position + numBytes
    nextAvailableOffset = OTFOffset32(unaligned.toLong + (alignment.toLong - (unaligned.toLong % alignment.toLong)))
    prev
  }

  override def allocate(data: Data): Offset = {
    val allocation = super.allocate(data)
    //    println(s"Allocated 0x${allocation.position.toLong.toHexString.reverse.padTo(8, '0').reverse} with 0x${data.length(this).toLong.toHexString.reverse.padTo(4, '0').reverse} (0x${(allocation.position.toLong + data.length(this).toLong).toHexString.reverse.padTo(8, '0').reverse}) to $data")
    allocation
  }

  def getBytes: Array[UByte] = {
    val m: Int = byteMap.map((t) => t._1.position.toInt).max
    (0 to m).map((i) => byteMap.getOrElse(OTFOffset32(i), UByte(0))).toArray
  }

}
