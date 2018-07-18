// 本文件实际不需要，因IDEA不能识别全局依赖，单独添加
name := "chapd-type-level"

version := "1.0"

scalaVersion := "2.11.8"

organization := "Scala in ALG & design pattern"

// append options passed to the Scala compiler
scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1",
  "junit" % "junit" % "4.12",
  "org.slf4j" % "slf4j-api" % "1.7.22",
  "org.scala-lang" % "scala-compiler" % "2.11.8",
  "org.scala-lang" % "scala-reflect" % "2.11.8",

  // 宏依赖
  "org.typelevel" %% "cats-core" % "1.0.1",
  "org.typelevel" %% "cats-kernel" % "1.0.1",
  "org.typelevel" %% "cats-macros" % "1.0.1",
  "org.typelevel" %% "macro-compat" % "1.1.1",

  "org.apache.logging.log4j" % "log4j-core" % "2.7",
  "org.apache.logging.log4j" % "log4j-api" % "2.7",
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.7",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0"
)
