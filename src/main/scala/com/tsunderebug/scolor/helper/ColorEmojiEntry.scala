package com.tsunderebug.scolor.helper

import java.awt.image.BufferedImage
import java.io.{ByteArrayInputStream, StringWriter}
import javax.imageio.ImageIO
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

import com.tsunderebug.scolor.otf.tables.color.OTFsRGBPNG
import com.tsunderebug.scolor.otf.tables.color.apple.OTFAppleGlyphData
import com.tsunderebug.scolor.otf.tables.color.google.{OTFGoogleGlyphData, OTFGoogleSmallGlyphMetrics}
import org.w3c.dom.Document
import spire.math.UByte

case class ColorEmojiEntry(main: Document, subs: Map[String, Document]) {

  /**
    * Used for drawing the glyph in places
    *
    * @return A raster of the glyph
    */
  def bitmaps: Map[String, (OTFGoogleGlyphData, OTFAppleGlyphData)] = {
    val a = subs + ("" -> main)
    a.map {
      case (s, f) =>
        val ds = f.toXmlString
        val docString = new ByteArrayInputStream(ds.getBytes("UTF-8"))
        val tbi = ImageIO.read(docString)
        val wtoh = tbi.getWidth.toDouble / tbi.getHeight.toDouble
        val wmside = tbi.getWidth > tbi.getHeight
        val bd: (Int, Int) = if (wmside) {
          (Math.max(256, tbi.getWidth), Math.max(256 * wtoh, tbi.getHeight).toInt)
        } else {
          (Math.max(256 / wtoh, tbi.getWidth).toInt, Math.max(256, tbi.getHeight))
        }
        val bi = new BufferedImage(bd._1, bd._2, BufferedImage.TYPE_INT_ARGB)
        bi.getGraphics.drawImage(tbi, 0, 0, bd._1, bd._2, null)
        val md = Math.max(bi.getWidth, bi.getHeight)
        val p = OTFsRGBPNG(bi)
        val d = OTFGoogleGlyphData(
          OTFGoogleSmallGlyphMetrics(
            UByte(bi.getHeight - 1),
            UByte(bi.getWidth - 1),
            (md - bi.getWidth).toByte,
            (md - bi.getHeight).toByte,
            UByte(bi.getWidth - 1)
          ),
          p
        )
        (s, (d, OTFAppleGlyphData(0, 0, OTFsRGBPNG(tbi))))
    }
  }

  def toScalable: Map[String, _] = { // TODO make Tuple for SVG/COLR data


    Map() // TODO When SVG and COLR tables are worked on
  }

  implicit class StringableDocument(d: Document) {

    def toXmlString: String = {
      val domSource = new DOMSource(d)
      val writer = new StringWriter
      val result = new StreamResult(writer)
      val tf = TransformerFactory.newInstance
      val transformer = tf.newTransformer
      transformer.transform(domSource, result)
      writer.toString
    }

  }

}
