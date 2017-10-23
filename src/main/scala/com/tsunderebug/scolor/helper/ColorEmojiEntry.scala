package com.tsunderebug.scolor.helper

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
        val bi = ImageIO.read(f)
        val md = Math.max(bi.getWidth, bi.getHeight)
        val p = OTFsRGBPNG(bi)
        val d = OTFGoogleGlyphData(
          OTFGoogleSmallGlyphMetrics(
            UByte(bi.getHeight),
            UByte(bi.getWidth),
            (md - bi.getWidth).toByte,
            (md - bi.getHeight).toByte,
            UByte(bi.getWidth)
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
