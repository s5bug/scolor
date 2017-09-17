package com.tsunderebug.scolor

import com.tsunderebug.scolor.table.Table
import spire.math.UInt

trait Font {

  def characterMap: Map[Seq[Codepoint], Glyph]
  def characterMapTable: Table
  def nextAvailableOffset(dataLen: UInt): Offset

}
