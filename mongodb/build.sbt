organization := "cn.galudisu"

name := "akka-persistence-reactivemongo"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.4"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

resolvers += Resolver.sonatypeRepo("snapshots")


libraryDependencies ++= Seq(
  "org.reactivemongo" %% "reactivemongo" % "0.12.7",

  "org.scalatest" %% "scalatest" % "3.0.1",
  "org.scalaz" %% "scalaz-core" % "7.2.16",
  "junit" % "junit" % "4.12",
  "org.slf4j" % "slf4j-api" % "1.7.22",
  "org.apache.logging.log4j" % "log4j-core" % "2.7",
  "org.apache.logging.log4j" % "log4j-api" % "2.7",
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.7",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",

  "com.typesafe.akka" %% "akka-actor" % "2.5.6",
  "com.typesafe.akka" %% "akka-slf4j" % "2.5.6",
  "com.typesafe.akka" %% "akka-persistence" % "2.5.6",
  "com.typesafe.akka" %% "akka-http" % "10.0.10",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.10",
  "com.typesafe.akka" %% "akka-remote" % "2.5.6",
  "com.typesafe.akka" %% "akka-cluster" % "2.5.6",
  "com.typesafe.akka" %% "akka-cluster-sharding" % "2.5.6",
  "org.scaldi" %% "scaldi-akka" % "0.5.8",

  // level db for local persistence
  "org.iq80.leveldb"            % "leveldb"          % "0.7",
  "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8"
)

publishMavenStyle := true

publishArtifact in Test := false
