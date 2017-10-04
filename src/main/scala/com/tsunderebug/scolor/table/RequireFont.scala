package com.tsunderebug.scolor.table

import com.tsunderebug.scolor.Font

trait RequireFont[F <: Font, D <: EnclosingSectionDataType] extends EnclosingSectionDataType {

  def apply(f: F): D

}
