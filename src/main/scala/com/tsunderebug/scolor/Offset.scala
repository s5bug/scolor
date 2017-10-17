package com.tsunderebug.scolor

import com.tsunderebug.scolor.table.SectionDataType
import spire.math.UInt

/**
  * Marks an Offset. This is more than a type alias for UInt for safety/extension in classes that create 16bit/32bit
  * offset params.
  */
trait Offset extends SectionDataType {

  def position: UInt

}
