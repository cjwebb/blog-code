name := "learning-akka-streams"
version := "0.0.1"
scalaVersion := "2.11.8"

val akkaVersion = "2.4.7"
libraryDependencies ++=
  "com.typesafe.akka" %% "akka-actor" % akkaVersion ::
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test" ::
  "com.typesafe.akka" %% "akka-stream" % akkaVersion ::
  "org.scalatest" %% "scalatest" % "2.2.6" % "test" ::
  Nil

