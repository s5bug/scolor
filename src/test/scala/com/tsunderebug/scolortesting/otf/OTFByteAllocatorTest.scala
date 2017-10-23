package com.tsunderebug.scolor.otf

import com.tsunderebug.scolor.Offset
import com.tsunderebug.scolor.otf.types.OTFOffset32
import org.scalatest.{FlatSpec, PrivateMethodTester}
import spire.math.UInt

class OTFByteAllocatorTest extends FlatSpec with PrivateMethodTester {
  
  /* use PrivateMethodTester to access private values in class */
  val nextAvailableOffset: PrivateMethod[Offset] = PrivateMethod[Offset]('nextAvailableOffset)

  "allocate" should "move the next available offset correctly" in {
    val otfByteAllocator = new OTFByteAllocator(new OpenTypeFont(Nil))
    otfByteAllocator.allocate(UInt(10))

    val nextOffset = otfByteAllocator invokePrivate nextAvailableOffset()
    assertResult(UInt(10))(nextOffset.position)
    assertResult(OTFOffset32(10))(nextOffset)
  }

  it should "return an offset describing the next open space these bytes can fit" in {
    val otfByteAllocator = new OTFByteAllocator(new OpenTypeFont(Nil))
    
    /* first we expect 0 */
    val firstOffset = otfByteAllocator.allocate(UInt(10))
    assertResult(OTFOffset32(0))(firstOffset)
    assertResult(UInt(0))(firstOffset.position)

    /* but after we allocate 10 above, we'll expect to be told we can allocate our
     * next set of bytes to the 10th position (which is 1 higher than our previous
     * allocation since we're 0-based)
     */
    val secondOffset = otfByteAllocator.allocate(UInt(6))
    assertResult(OTFOffset32(10))(secondOffset)
    assertResult(UInt(10))(secondOffset.position)
  }
  
}