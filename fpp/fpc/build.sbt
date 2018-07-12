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

slick <<= slickCodeGenTask

sourceGenerators in Compile <+= slickCodeGenTask

lazy val slick = TaskKey[Seq[File]]("gen-tables")
lazy val slickCodeGenTask = (sourceManaged, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
  val outputDir = (dir / "main/scala").getPath
  val username = "root"
  val password = "8612"
  val url = "jdbc:mysql:///test?nullNamePatternMatchesAll=true&serverTimezone=UTC"
  val jdbcDriver = "com.mysql.cj.jdbc.Driver"
  val slickDriver = "slick.jdbc.MySQLProfile"
  val pkg = "cn.galudisu.fp.models"
  toError(r.run("slick.codegen.SourceCodeGenerator", cp.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg, username, password), s.log))
  val fname = outputDir + "/" + "cn/galudisu/fp/models" + "/Tables.scala"
  Seq(file(fname))
}
