package com.tsunderebug.scolortesting

import com.tsunderebug.scolor.otf.OpenTypeFont
import com.tsunderebug.scolor.otf.tables.OTFNAMETable
import com.tsunderebug.scolor.otf.types.gen.{MacLanguage, WindowsLanguage}
import com.tsunderebug.scolor.otf.types.{OTFNameRecord, OTFString}
import org.scalatest.FlatSpec

class OpenTypeFontTest extends FlatSpec {

  val expected: String =
    """4f 54 54 4f 00 01 00 10 00 01 00 00 6e 61 6d 65
      |57 17 d3 91 00 00 00 1c 00 00 00 b8 00 00 00 00
      |08 00 66 00 03 00 01 04 09 00 04 00 0b 01 1d 00
      |03 00 01 04 13 00 04 00 0c 01 28 00 03 00 01 04
      |1d 00 04 00 0b 01 34 00 01 00 00 00 04 00 00 00
      |0b 01 1d 00 01 00 04 00 04 00 00 00 0c 01 28 00
      |01 00 05 00 04 00 00 00 0b 01 34 00 03 00 01 04
      |11 00 04 00 07 01 3f 00 01 00 0b 00 04 00 01 00
      |07 01 3f 00 48 00 65 00 6c 00 6c 00 6f 00 20 00
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
              OTFNameRecord(d = WindowsLanguage.`English`.`United States`, content = OTFString("Hello World")),
              OTFNameRecord(d = WindowsLanguage.`Dutch`.`Netherlands`, content = OTFString("Hallo Wereld")),
              OTFNameRecord(d = WindowsLanguage.`Swedish`.`Sweden`, content = OTFString("Hej Världen")),
              OTFNameRecord(d = MacLanguage(MacLanguage.Script.`Roman`, MacLanguage.Language.`English`), content = OTFString("Hello World")),
              OTFNameRecord(d = MacLanguage(MacLanguage.Script.`Roman`, MacLanguage.Language.`Dutch`), content = OTFString("Hallo Wereld")),
              OTFNameRecord(d = MacLanguage(MacLanguage.Script.`Roman`, MacLanguage.Language.`Swedish`), content = OTFString("Hej Världen")),
              OTFNameRecord(d = WindowsLanguage.`Japanese`.`Japan`, content = OTFString("こんにちは世界")),
              OTFNameRecord(d = MacLanguage(MacLanguage.Script.`Japanese`, MacLanguage.Language.`Japanese`), content = OTFString("こんにちは世界")),
            )
          )
        )
      ).getBytes.map((b) => "%02x".format(b.toInt)).grouped(16).map(_.mkString(" ")).mkString("\n")

    assertResult(expected)(openTypeFontString)
  }

}
