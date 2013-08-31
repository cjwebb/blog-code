name := "akka-camel-and-activemq"

version := "1.0"

scalaVersion := "2.10.2"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2.1",
  "com.typesafe.akka" %% "akka-camel" % "2.2.1",
  "org.apache.activemq" % "activemq-camel" % "5.8.0"
)
