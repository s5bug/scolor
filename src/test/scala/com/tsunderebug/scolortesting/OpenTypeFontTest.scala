package com.tsunderebug.scolortesting

import com.tsunderebug.scolor.otf.OpenTypeFont
import com.tsunderebug.scolor.otf.tables.OTFNAMETable
import com.tsunderebug.scolor.otf.types.OTFNameRecord
import com.tsunderebug.scolor.otf.types.gen.{MacLanguage, WindowsLanguage}
import org.scalatest.FlatSpec

class OpenTypeFontTest extends FlatSpec {

  val expected: String =
    """4f 54 54 4f 00 01 00 10 00 01 00 00 6e 61 6d 65
      |5a 2b d3 cd 00 00 00 1c 00 00 01 0a 00 00 00 00
      |08 00 66 00 03 00 01 04 09 00 04 00 16 01 6f 00
      |03 00 01 04 13 00 04 00 18 01 85 00 03 00 01 04
      |1d 00 04 00 16 01 9d 00 01 00 00 00 00 00 04 00
      |0b 01 6f 00 01 00 00 00 04 00 04 00 0c 01 85 00
      |01 00 00 00 05 00 04 00 0c 01 9d 00 03 00 01 04
      |11 00 04 00 0e 01 b3 00 01 00 01 00 0b 00 04 00
      |15 01 b3 00 48 00 65 00 6c 00 6c 00 6f 00 20 00
      |57 00 6f 00 72 00 6c 00 64 00 48 00 61 00 6c 00
      |6c 00 6f 00 20 00 57 00 65 00 72 00 65 00 6c 00
      |64 00 48 00 65 00 6a 00 20 00 56 00 e4 00 72 00
      |6c 00 64 00 65 00 6e 00 48 00 65 00 6c 00 6c 00
      |6f 00 20 00 57 00 6f 00 72 00 6c 00 64 00 48 00
      |61 00 6c 00 6c 00 6f 00 20 00 57 00 65 00 72 00
      |65 00 6c 00 64 00 48 00 65 00 6a 00 20 00 56 00
      |e4 00 72 00 6c 00 64 00 65 00 6e 30 53 30 93 30
      |6b 30 61 30 6f 4e 16 75 4c 30 53 30 93 30 6b 30
      |61 30 6f 4e 16 75 4c""".stripMargin

  "OpenTypeFont" should "create the expected bytes" in {
    val openTypeFontString =
      new OpenTypeFont(
        Seq(
          OTFNAMETable(
            Seq(
              OTFNameRecord(d = WindowsLanguage.`English`.`United States`, content = "Hello World"),
              OTFNameRecord(d = WindowsLanguage.`Dutch`.`Netherlands`, content = "Hallo Wereld"),
              OTFNameRecord(d = WindowsLanguage.`Swedish`.`Sweden`, content = "Hej Världen"),
              OTFNameRecord(d = MacLanguage(MacLanguage.Script.`Roman`, MacLanguage.Language.`English`), content = "Hello World"),
              OTFNameRecord(d = MacLanguage(MacLanguage.Script.`Roman`, MacLanguage.Language.`Dutch`), content = "Hallo Wereld"),
              OTFNameRecord(d = MacLanguage(MacLanguage.Script.`Roman`, MacLanguage.Language.`Swedish`), content = "Hej Världen"),
              OTFNameRecord(d = WindowsLanguage.`Japanese`.`Japan`, content = "こんにちは世界"),
              OTFNameRecord(d = MacLanguage(MacLanguage.Script.`Japanese`, MacLanguage.Language.`Japanese`), content = "こんにちは世界"),
            )
          )
        )
      ).getBytes.map((b) => "%02x".format(b.toInt)).grouped(16).map(_.mkString(" ")).mkString("\n")

    assertResult(expected)(openTypeFontString)
  }

}
