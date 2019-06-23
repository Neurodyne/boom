val scalaVer      = "2.12.8"
val json4sVersion = "3.6.6"

// Provide a managed dependency on X if -DXVersion="" is supplied on the command line.
val defaultVersions = Map(
  "firrtl"  -> "1.2-SNAPSHOT",
  "chisel3" -> "3.2-SNAPSHOT"
)

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-jackson" % json4sVersion
)

scalacOptions ++= Seq(
  "-Xsource:2.11",
  //"-Xlint",
  "-Xverify",
  "-feature",
  "-deprecation",
  "-explaintypes",
  "-unchecked",
  "-Xfuture",
  "-encoding",
  "UTF-8",
  //"-Yrangepos",
  //"-Xlint:_,-type-parameter-shadow",
  //"-Ywarn-numeric-widen",
  //"-Ywarn-unused",
  //"-Ywarn-value-discard",
  "-language:_"
  //"-language:higherKinds",
  //"-language:existentials",
  //"-language:reflectiveCalls",
  //"-Yno-adapted-args",
  //"-Ypartial-unification",
  //"-Xfatal-warnings",
  //"-Xlint:-infer-any,_",
  //"-Ywarn-value-discard",
  //"-Ywarn-numeric-widen",
  //"-Ywarn-extra-implicit",
  //"-Ywarn-unused:_",
  //"-Ywarn-inaccessible",
  //"-Ywarn-nullary-override",
  //"-Ywarn-nullary-unit",
  //"-opt-inline-from:<source>",
  //"-opt-warnings",
  //"-opt:l:inline",
)

lazy val commonSettings = Seq(
  organization := "edu.berkeley.cs",
  version := "1.2",
  scalaVersion := scalaVer,
  crossScalaVersions := Seq("2.12.8", "2.11.12"),
  parallelExecution in Global := false,
  traceLevel := 5,
  maxErrors := 3,
  libraryDependencies ++= Seq("chisel3").map { dep: String =>
    "edu.berkeley.cs" %% dep % sys.props.getOrElse(dep + "Version", defaultVersions(dep))
  },
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
)

// A RootProject (not well-documented) tells sbt to treat the target directory
// as its own root project, reading its build settings. If we instead used the
// normal `project in file()` declaration, sbt would ignore all of rocket-chip's
// build settings, and therefore not understand that it has its own dependencies
// on chisel, etc.
lazy val core = RootProject(file("rocket-chip"))

lazy val top = (project in file("."))
  .dependsOn(core)
  .settings(commonSettings: _*)

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("chk", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")
