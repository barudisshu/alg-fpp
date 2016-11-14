import sbt.Keys._

logLevel := Level.Warn
addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.2")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.2")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.9.0")

addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "2.1.0")

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.0-RC5")

resolvers += Resolver.sonatypeRepo("public")

