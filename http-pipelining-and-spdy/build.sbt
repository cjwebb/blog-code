name := "http-pipelining-and-spdy"

version := "1.0"

scalaVersion := "2.10.2"

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io/"
)

libraryDependencies ++= Seq(
  "io.spray"            %   "spray-can"      % "1.1-M8",
  "io.spray"            %   "spray-routing"  % "1.1-M8",
  "io.spray"            %   "spray-testkit"  % "1.1-M8",
  "com.typesafe.akka"   %%  "akka-actor"     % "2.1.4",
  "com.typesafe.akka"   %%  "akka-testkit"   % "2.1.4",
  "org.scalatest"       %   "scalatest_2.10" % "1.9.1" % "test"
)
