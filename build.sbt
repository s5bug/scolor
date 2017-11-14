name := "Scolor"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "org.typelevel" %% "spire" % "0.14.1",
  "org.apache.xmlgraphics" % "batik-svg-dom" % "1.9.1",
  "org.apache.xmlgraphics" % "batik-transcoder" % "1.9.1",
  "org.apache.xmlgraphics" % "batik-extension" % "1.9.1",
  "org.apache.xmlgraphics" % "batik-rasterizer-ext" % "1.9.1",
  "com.twelvemonkeys.imageio" % "imageio-batik" % "3.3.2",
  "com.twelvemonkeys.imageio" % "imageio-core" % "3.3.2",
  "com.twelvemonkeys.imageio" % "imageio-metadata" % "3.3.2",
  "com.twelvemonkeys.common" % "common-lang" % "3.3.2",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "com.storm-enroute" %% "scalameter" % "0.8.2" % "test",
)

// Add ScalaMeter
testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")

// ScalaMeter recommends this in its getting started section
logBuffered := false