package com.tsunderebug.scolor.table

import com.tsunderebug.scolor.{Data, Font}
import spire.math.UByte

trait Table extends Data {

  def name: String
  def sections: Seq[Section]

  /**
    * The bytes of table data. The data does not have to be padded.
    * @return Table data as a byte array.
    */
  def getBytes(f: Font): Array[UByte]

}
