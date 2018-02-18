package com.tsunderebug.scolortesting

import java.io.{File, FileOutputStream}
import javax.xml.parsers.{DocumentBuilder, DocumentBuilderFactory}

import com.tsunderebug.scolor.helper.{ColorEmojiEntry, ColorEmojiFont}
import org.w3c.dom.Document
import spire.math.{UInt, UShort}

object RandomDebugging {

  val svgFile = new File("src/test/resources/acid.svg")
  val dbFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
  val dBuilder: DocumentBuilder = dbFactory.newDocumentBuilder()
  val doc: Document = dBuilder.parse(svgFile)

  def main(args: Array[String]): Unit = {
    val testfontfile = new File("testfont.otf")
    val writer = new FileOutputStream(testfontfile)
    writer.write(ColorEmojiFont(
      UShort(32),
      "test",
      1.0,
      System.currentTimeMillis(),
      Map(
        UInt("A".charAt(0).toInt) -> ColorEmojiEntry(doc, Map())
      )
    ).getBytes.map(_.signed))
    writer.close()
  }

}
