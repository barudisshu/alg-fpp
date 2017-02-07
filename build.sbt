name := "alg-fpp"

version := "1.0"

scalaVersion := "2.11.8"

organization := "Scala in ALG & design pattern"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.12",
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "mysql" % "mysql-connector-java" % "5.1.25",
  "org.scalatest" % "scalatest_2.11" % "3.0.1",
  "junit" % "junit" % "4.12",
  "org.apache.commons" % "commons-lang3" % "3.5",
  "org.hamcrest" % "hamcrest-all" % "1.3",
  "com.google.guava" % "guava" % "21.0",
  "org.mockito" % "mockito-all" % "1.9.5",
  "org.slf4j" % "slf4j-api" % "1.7.22",
  "org.apache.logging.log4j" % "log4j-core" % "2.7",
  "org.apache.logging.log4j" % "log4j-api" % "2.7",
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.7",
  "com.typesafe.scala-logging" % "scala-logging_2.11" % "3.5.0"
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