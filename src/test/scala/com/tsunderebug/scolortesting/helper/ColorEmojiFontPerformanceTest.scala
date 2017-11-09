package com.tsunderebug.scolor.helper

import org.scalameter.api._
import spire.math.{UInt, UShort}
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import java.io.File;


/** Use ForkedTime because it's supposed to give better reproduceable results
 * @see https://scalameter.github.io/home/gettingstarted/0.7/configuration/index.html
 */
object ColorEmojiFontPerformanceTest extends Bench.ForkedTime {
	
	val gen = Gen.unit("font")

	/* Preload the document because I assume that isn't part of what we're perf testing */
	val svgFile = new File("src/test/resources/acid.svg");
	val dbFactory = DocumentBuilderFactory.newInstance();
	val dBuilder = dbFactory.newDocumentBuilder();
	val doc = dBuilder.parse(svgFile);

	performance of "ColorEmojiFont" in {

		measure method "apply" in {
			/** Using defaults here, but we could have it produce charts if desired 
			 * @see https://scalameter.github.io/home/gettingstarted/0.7/reporters/index.html
			 */
			using(gen) in { _ =>
				ColorEmojiFont(
				  UShort(32),
				  "test",
				  Map(
				    UInt("A".charAt(0).toInt) -> ColorEmojiEntry(doc, Map())
				  )
				)
			}
		}

		// More tests here if desired.
	}

}