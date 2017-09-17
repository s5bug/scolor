package com.tsunderebug.scolor.otf

import com.tsunderebug.scolor.otf.types.Offset32
import com.tsunderebug.scolor.{Codepoint, Font, Glyph, Offset}
import spire.math.UInt

import scala.collection.mutable

class OpenTypeFont extends Font {

  private val cmapData: mutable.Map[Seq[Codepoint], Glyph] = mutable.Map()

  override def characterMap: Map[Seq[Codepoint], Glyph] = cmapData.toMap

  override def characterMapTable: OpenTypeCMAPTable = {
    null
  }

  override def nextAvailableOffset(dataLen: UInt): Offset = {
    Offset32(0)
  }

}
