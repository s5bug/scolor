val batikVersion = settingKey[String]("Batik Version")
val imageIOVersion = settingKey[String]("ImageIO Version")

lazy val root = (project in file(".")).settings(
  name := "Scolor",
  version := "0.1",
  scalaVersion := "2.12.5",
  batikVersion := "1.9.1",
  imageIOVersion := "3.3.2",
  libraryDependencies ++= Seq(
    "org.typelevel" %% "spire" % "0.14.1",
    "org.apache.xmlgraphics" % "batik-svg-dom" % batikVersion.value,
    "org.apache.xmlgraphics" % "batik-transcoder" % batikVersion.value,
    "org.apache.xmlgraphics" % "batik-extension" % batikVersion.value,
    "org.apache.xmlgraphics" % "batik-rasterizer-ext" % batikVersion.value,
    "com.twelvemonkeys.imageio" % "imageio-batik" % imageIOVersion.value,
    "com.twelvemonkeys.imageio" % "imageio-core" % imageIOVersion.value,
    "com.twelvemonkeys.imageio" % "imageio-metadata" % imageIOVersion.value,
    "com.twelvemonkeys.common" % "common-lang" % imageIOVersion.value,
    "org.scalatest" %% "scalatest" % "3.0.5" % "test",
    "com.storm-enroute" %% "scalameter" % "0.8.2" % "test"
  ),
  testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework"),
  logBuffered := false
)
