package com.tsunderebug.scolor.helper

import java.io.File
import javax.xml.parsers.{DocumentBuilder, DocumentBuilderFactory}

import org.scalameter.api._
import org.w3c.dom.Document
import spire.math.{UInt, UShort}


/** Use ForkedTime because it's supposed to give better reproduceable results
 * @see https://scalameter.github.io/home/gettingstarted/0.7/configuration/index.html
 */
object ColorEmojiFontPerformanceTest extends Bench.ForkedTime {

  val gen: Gen[Unit] = Gen.unit("font")

	/* Preload the document because I assume that isn't part of what we're perf testing */
  val svgFile = new File("src/test/resources/acid.svg")
  val dbFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
  val dBuilder: DocumentBuilder = dbFactory.newDocumentBuilder()
  val doc: Document = dBuilder.parse(svgFile)

	performance of "ColorEmojiFont" in {

		measure method "apply" in {
			/** Using defaults here, but we could have it produce charts if desired 
			 * @see https://scalameter.github.io/home/gettingstarted/0.7/reporters/index.html
			 */
			using(gen) in { _ =>
				ColorEmojiFont(
				  UShort(32),
				  "test",
					1.0,
					System.currentTimeMillis(),
				  Map(
				    UInt("A".charAt(0).toInt) -> ColorEmojiEntry(doc, Map())
				  )
				)
			}
		}

		// More tests here if desired.
	}

}