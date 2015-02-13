import sbt._
import Keys._

object DiabetesBuild extends Build
{
    lazy val root = Project(
        id = "Diabetes",
        base = file( "." ),
        settings = Defaults.defaultSettings ++ Seq(
            scalaVersion := "2.11.5",
            libraryDependencies ++= Seq(
                "org.scalatest" %% "scalatest" % "2.2.1" % "test",
                "org.scalacheck" %% "scalacheck" % "1.12.1" % "test",
                "org.scalafx" %% "scalafx" % "8.0.0-R4"
            )
        )
    )
}
