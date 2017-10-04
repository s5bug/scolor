package com.tsunderebug.scolor.table

trait RequireTable[T <: Table, D <: EnclosingSectionDataType] {

  def apply(t: T): D

}
