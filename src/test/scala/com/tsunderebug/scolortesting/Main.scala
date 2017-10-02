package com.tsunderebug.scolortesting

import com.tsunderebug.scolor.otf.OpenTypeFont
import com.tsunderebug.scolor.otf.tables.OpenTypeCMAPTable

object Main {

  def main(args: Array[String]): Unit = {
    println(OpenTypeFont(
      Seq(
        OpenTypeCMAPTable(
          Seq()
        )
      )
    ).getBytes.map(_.toInt.toHexString).mkString(" "))
  }

}
