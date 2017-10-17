package com.tsunderebug.scolortesting

import com.tsunderebug.scolor.otf.OpenTypeFont
import com.tsunderebug.scolor.otf.tables.OpenTypeNAMETable
import com.tsunderebug.scolor.otf.types.{NameRecord, OTFString}
import com.tsunderebug.scolor.otf.types.gen.WindowsLanguage

import org.scalatest.FlatSpec

class OpenTypeFontTest extends FlatSpec {

  val expected = 
  """|4f 54 54 4f 00 01 00 10 00 01 00 00 6e 61 6d 65
     |07 bc 13 0c 00 00 00 1c 00 00 00 4c 00 00 00 00
     |00 00 00 03 00 2a 00 03 00 01 04 09 00 04 00 0b
     |00 75 00 03 00 01 04 13 00 04 00 0c 00 80 00 03
     |00 01 04 1d 00 04 00 0b 00 8c 00 48 00 65 00 6c
     |00 6c 00 6f 00 20 00 57 00 6f 00 72 00 6c 00 64
     |00 48 00 61 00 6c 00 6c 00 6f 00 20 00 57 00 65
     |00 72 00 65 00 6c 00 64 00 48 00 65 00 6a 00 20
     |00 56 00 e4 00 72 00 6c 00 64 00 65 00 6e""".stripMargin

  "OpenTypeFont" should "create the expected bytes" in {
    val openTypeFontString = 
      OpenTypeFont(
        Seq(
          OpenTypeNAMETable(
            Seq(
              NameRecord(d = WindowsLanguage.`English`.`United States`, content = OTFString("Hello World")),
              NameRecord(d = WindowsLanguage.`Dutch`.`Netherlands`, content = OTFString("Hallo Wereld")),
              NameRecord(d = WindowsLanguage.`Swedish`.`Sweden`, content = OTFString("Hej Världen")),
            )
          )
        )
      ).getBytes.map((b) => "%02x".format(b.toInt)).grouped(16).map(_.mkString(" ")).mkString("\n")

    assertResult(expected)(openTypeFontString)
  }

}
