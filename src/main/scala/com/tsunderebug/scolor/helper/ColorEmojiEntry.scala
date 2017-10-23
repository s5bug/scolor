package com.tsunderebug.scolor.helper

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import com.tsunderebug.scolor.otf.tables.color.{OTFGoogleGlyphData, OTFGoogleSmallGlyphMetrics, OTFsRGBPNG}
import org.apache.batik.anim.dom.{SAXSVGDocumentFactory, SVGDOMImplementation}
import org.apache.batik.util.XMLResourceDescriptor
import spire.math.UByte

case class ColorEmojiEntry(main: File, subs: Map[String, File]) {

  /**
    * Used for drawing the glyph in places
    *
    * @return A raster of the glyph
    */
  def bitmaps: Map[String, OTFGoogleGlyphData] = {
    val a = subs + ("" -> main)
    a.map {
      case (s, f) =>
        val tbi = ImageIO.read(f)
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
        (s, d)
    }
  }

  def toScalable: Map[String, _] = {
    val parser = XMLResourceDescriptor.getXMLParserClassName
    val impl = SVGDOMImplementation.getDOMImplementation
    val f = new SAXSVGDocumentFactory(parser)
    val uri = "http://www.example.org/diagram.svg"
    val doc = f.createDocument(uri)

    Map() // TODO When SVG and COLR tables are worked on
  }

}
