package com.tsunderebug.scolor.table

import com.tsunderebug.scolor.ByteAllocator
import spire.math.UByte

trait Table extends EnclosingSectionDataType {

  /**
    * @return the table tag/name/identifier
    */
  def name: String

  /**
    * @param b The byte allocator
    * @return the sections/partitions/rows of a table
    */
  def sections(b: ByteAllocator): Seq[Section]

  /**
    * The bytes of table data. The data does not have to be padded.
    * @return Table data as a byte array.
    */
  def getBytes(b: ByteAllocator): Array[UByte]

}
