// 本文件实际不需要，因IDEA不能识别全局依赖，单独添加
name := "chapa-Slick"

version := "1.0"

scalaVersion := "2.11.8"

organization := "Scala in ALG & design pattern"

// append options passed to the Scala compiler
scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1",
  "junit" % "junit" % "4.12",
  "org.slf4j" % "slf4j-api" % "1.7.22",
  "org.apache.logging.log4j" % "log4j-core" % "2.7",
  "org.apache.logging.log4j" % "log4j-api" % "2.7",
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.7",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",

  "mysql" % "mysql-connector-java" % "6.0.6",
  "com.zaxxer" % "HikariCP" % "2.7.8",

  "com.typesafe.slick" %% "slick" % "3.2.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.1",
  "com.typesafe.slick" %% "slick-codegen" % "3.2.1"

)