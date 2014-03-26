name := "aggregation-services"

version := "1.0"

scalaVersion := "2.10.4"

resolvers ++= Seq(
  "spray" at "http://repo.spray.io/",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Mandubian repository releases" at "https://github.com/mandubian/mandubian-mvn/raw/master/releases/"
)

libraryDependencies ++= Seq(
  "io.spray" % "spray-util" % "1.2.0",
  "io.spray" %  "spray-can" % "1.2.0",
  "io.spray" %  "spray-client" % "1.2.0",
  "io.spray" %  "spray-httpx" % "1.2.0",
  "com.typesafe.akka" %% "akka-actor" % "2.2.3",
  "org.scalaz" %% "scalaz-core" % "7.0.4",
  "com.typesafe.play"  %% "play-json"        % "2.2.1",
  "play-json-zipper"  %% "play-json-zipper" % "1.0"
)
