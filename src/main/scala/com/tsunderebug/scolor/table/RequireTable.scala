package com.tsunderebug.scolor.table

/**
  * Extend this if you have a table and data that requires that table. The table can call apply with itself and get the
  * tabled element.
  *
  * @tparam T The table type
  * @tparam D The tabled data type
  */
trait RequireTable[T <: Table, D <: EnclosingSectionDataType] {

  def apply(t: T): D

}
