package com.tsunderebug.scolortesting

import com.tsunderebug.scolor.otf.OpenTypeFont
import com.tsunderebug.scolor.otf.tables.OpenTypeNAMETable
import com.tsunderebug.scolor.otf.types.{NameRecord, OTFString}
import com.tsunderebug.scolor.otf.types.gen.WindowsLanguage

object Main {

  def main(args: Array[String]): Unit = {
    println(OpenTypeFont(
      Seq(
        OpenTypeNAMETable(
          Seq(
            NameRecord(d = WindowsLanguage.`English`.`United States`, content = OTFString("Hello World")),
            NameRecord(d = WindowsLanguage.`Dutch`.`Netherlands`, content = OTFString("Hallo Wereld")),
            NameRecord(d = WindowsLanguage.`Swedish`.`Sweden`, content = OTFString("Hej Världen")),
          )
        )
      )
    ).getBytes.map((b) => "%02x".format(b.toInt)).grouped(16).map(_.mkString(" ")).mkString("\n"))
  }

}
