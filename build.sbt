name := "Scolor"

version := "0.1"

scalaVersion := "2.12.4"

val batikVersion = "1.9.1"
val imageIOVersion = "3.3.2"

libraryDependencies ++= Seq(
  "org.typelevel" %% "spire" % "0.14.1",
  "org.apache.xmlgraphics" % "batik-svg-dom" % batikVersion,
  "org.apache.xmlgraphics" % "batik-transcoder" % batikVersion,
  "org.apache.xmlgraphics" % "batik-extension" % batikVersion,
  "org.apache.xmlgraphics" % "batik-rasterizer-ext" % batikVersion,
  "com.twelvemonkeys.imageio" % "imageio-batik" % imageIOVersion,
  "com.twelvemonkeys.imageio" % "imageio-core" % imageIOVersion,
  "com.twelvemonkeys.imageio" % "imageio-metadata" % imageIOVersion,
  "com.twelvemonkeys.common" % "common-lang" % imageIOVersion,
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "com.storm-enroute" %% "scalameter" % "0.8.2" % "test",
)

// Add ScalaMeter
testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")

// ScalaMeter recommends this in its getting started section
logBuffered := false