name := "Scolor"

version := "0.1"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  "org.typelevel" %% "spire" % "0.14.1",
  "org.apache.xmlgraphics" % "batik-svg-dom" % "1.9.1",
  "org.apache.xmlgraphics" % "batik-transcoder" % "1.9.1",
  "com.twelvemonkeys.imageio" % "imageio-batik" % "3.3.2",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
)
