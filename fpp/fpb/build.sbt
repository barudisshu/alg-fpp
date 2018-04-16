// 本文件实际不需要，因IDEA不能识别全局依赖，单独添加
name := "chapa-Shapeless"

version := "1.0"

scalaVersion := "2.12.4"

organization := "Scala in ALG & design pattern"

// append options passed to the Scala compiler
scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1",
  "junit" % "junit" % "4.12",
  "com.chuusai" %% "shapeless" % "2.3.2",
  "org.slf4j" % "slf4j-api" % "1.7.22",
  "org.apache.logging.log4j" % "log4j-core" % "2.7",
  "org.apache.logging.log4j" % "log4j-api" % "2.7",
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.7",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0"
)
