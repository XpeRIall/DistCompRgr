name := "CourseWork"

version := "0.1"

scalaVersion := "2.13.4"

libraryDependencies ++= Seq(
	"org.typelevel" %% "cats-effect" % "2.3.1",
	"io.estatico" %% "newtype" % "0.4.4",
	"io.chrisdavenport" %% "log4cats-slf4j" % "1.1.1",
	"org.slf4j" % "jcl-over-slf4j" % "1.7.21"
)
excludeDependencies += "commons-logging" % "commons-logging"

scalacOptions in Global += "-Ymacro-annotations"
