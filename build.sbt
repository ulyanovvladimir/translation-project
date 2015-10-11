name := """playframework-manual-ru"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayDocsPlugin)

enablePlugins(JavaAppPackaging)