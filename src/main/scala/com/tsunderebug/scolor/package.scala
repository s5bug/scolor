package com.tsunderebug

import java.io.StringWriter

import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import org.w3c.dom.Document

package object scolor {

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
