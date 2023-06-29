
import sun.security.tools.PathList
import sun.security.tools.PathList._

name := "CARTCell"

version := "0.0.1"
scalaVersion := "2.12.1"
crossScalaVersions := Seq("2.12.1", "2.13.0")

libraryDependencies += "io.github.pityka" %% "nspl-awt" % "0.0.21" % "provided"
libraryDependencies ++= Seq(
  "org.scala-saddle" %% "saddle-core" % "1.3.5-SNAPSHOT" % "provided")
libraryDependencies += "io.github.pityka" %% "nspl-saddle" % "0.0.21" % "provided"

resolvers ++= Seq(
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases"
)
resolvers += Opts.resolver.sonatypeSnapshots
