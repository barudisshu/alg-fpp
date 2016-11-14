name := "alg-fpp"

version := "1.0"

scalaVersion := "2.11.8"

organization := "Scala in ALG & design pattern"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.12",
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "mysql" % "mysql-connector-java" % "5.1.25"
)

resolvers ++= Seq(
  "Spray repository" at "http://repo.spray.io",
  "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
  "releases" at "http://dl.bintray.com/sbt/sbt-plugin-releases/",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Snapshots Repository" at "http://repo.typesafe.com/typesafe/snapshots/",
  "Typesafe Snapshots Maven Repository" at "http://repo.typesafe.com/typesafe/maven-releases/"
)

unmanagedSourceDirectories in Compile <<= baseDirectory { base =>
  Seq(
    base / "src/main/scala"
  )
}
scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")