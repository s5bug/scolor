package com.tsunderebug.scolor.table

import com.tsunderebug.scolor.Font

/**
  * Extend this if you have a font and data that requires that font. The font can call apply with itself and get the
  * fonted element.
  *
  * @tparam F The font type
  * @tparam D The fonted data type
  */
trait RequireFont[F <: Font, D <: EnclosingSectionDataType] extends EnclosingSectionDataType {

  def apply(f: F): D

}
