package com.tsunderebug.scolor.table

trait RequireEnclosingSectionDataType[E <: EnclosingSectionDataType, D <: EnclosingSectionDataType] {

  def apply(t: E): D

}
