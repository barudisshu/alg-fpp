import slick.jdbc.MySQLProfile

name := "alg-fpp"
version := "1.0"
scalaVersion in ThisBuild := "2.12.8"
organization in ThisBuild := "Scala in ALG & design pattern"

lazy val global = project
  .in(file("."))
  .settings(settings)
  .disablePlugins(AssemblyPlugin)
  .aggregate(fpp, akka, algorithm)

lazy val fpp = project
  .settings(
    name := "fpp",
    settings,
    libraryDependencies ++= commonDependencies
  )
  .disablePlugins(AssemblyPlugin)
  .aggregate(fp1, fp2, fp3, fp4, fp5, fp6, fp7, fp8, fp9, fpa, fpc, fpd)

lazy val fp1 = project
  .in(file("fpp/fp1"))
  .settings(
    name := "chap1-grokking-functional-way",
    settings,
    libraryDependencies ++= commonDependencies
  )
  .disablePlugins(AssemblyPlugin)

lazy val fp2 = project
  .in(file("fpp/fp2"))
  .settings(
    name := "chap2-singletons-factories-and-builders",
    settings,
    libraryDependencies ++= commonDependencies
  )
  .disablePlugins(AssemblyPlugin)

lazy val fp3 = project
  .in(file("fpp/fp3"))
  .settings(
    name := "chap3-recursion-and-chasing-your-own-tail",
    settings,
    libraryDependencies ++= commonDependencies
  )
  .disablePlugins(AssemblyPlugin)

lazy val fp4 = project
  .in(file("fpp/fp4"))
  .settings(
    name := "chap4-lazy-sequences-being-lazy-being-good",
    settings,
    libraryDependencies ++= commonDependencies
  )
  .disablePlugins(AssemblyPlugin)
lazy val fp5 = project
  .in(file("fpp/fp5"))
  .settings(
    name := "chap5-taming-multiple-inheritance-with-traits",
    settings,
    libraryDependencies ++= commonDependencies
  )
  .disablePlugins(AssemblyPlugin)

lazy val fp6 = project
  .in(file("fpp/fp6"))
  .settings(
    name := "chap6-currying-favors-with-your-code",
    settings,
    libraryDependencies ++= commonDependencies
  )
  .disablePlugins(AssemblyPlugin)

lazy val fp7 = project
  .in(file("fpp/fp7"))
  .settings(
    name := "chap7-Monoids",
    settings,
    libraryDependencies ++= commonDependencies
  )
  .disablePlugins(AssemblyPlugin)

lazy val fp8 = project
  .in(file("fpp/fp8"))
  .settings(
    name := "chap8-Monads",
    settings,
    libraryDependencies ++= commonDependencies
  )
  .disablePlugins(AssemblyPlugin)

lazy val fp9 = project
  .in(file("fpp/fp9"))
  .settings(
    name := "chap9-Scalaz",
    settings,
    libraryDependencies ++= commonDependencies
  )
  .disablePlugins(AssemblyPlugin)

lazy val fpa = project
  .in(file("fpp/fpa"))
  .settings(
    name := "chapa-Cats",
    settings,
    libraryDependencies ++= commonDependencies
  )
  .disablePlugins(AssemblyPlugin)

lazy val fpb = project
  .in(file("fpp/fpb"))
  .settings(
    name := "chapb-Shapeless",
    settings,
    libraryDependencies ++= commonDependencies ++ Seq(dependencies.shapeless)
  )
  .disablePlugins(AssemblyPlugin)

lazy val fpc = project
  .in(file("fpp/fpc"))
  .settings(
    name := "chapc-Slick",
    settings ++ Seq(
      sourceGenerators in Compile += slickCodegen,
      slickCodegenDatabaseUrl := "jdbc:mysql:///test?nullNamePatternMatchesAll=true&serverTimezone=UTC",
      slickCodegenDatabaseUser := "root",
      slickCodegenDatabasePassword := "root",
      slickCodegenJdbcDriver := "com.mysql.cj.jdbc.Driver",
      slickCodegenDriver := MySQLProfile,
      slickCodegenOutputPackage := "cn.galudisu.fp.modules",
      slickCodegenOutputFile := "Tables.scala"
    ),
    libraryDependencies ++= commonDependencies ++ slickDependencies
  )
  .disablePlugins(AssemblyPlugin)
  .enablePlugins(CodegenPlugin)

lazy val fpd = project
  .in(file("fpp/fpd"))
  .settings(
    name := "chapd-type-level",
    settings,
    libraryDependencies ++= commonDependencies ++ catsDependencies
  )
  .disablePlugins(AssemblyPlugin)

lazy val akka = project
  .settings(
    name := "akka-ddd",
    settings,
    assemblySettings,
    libraryDependencies ++= commonDependencies ++ akkaDependencies ++ Seq(
      dependencies.monocleCore,
      dependencies.monocleMacro,
      dependencies.scaldi,
      dependencies.leveldb,
      dependencies.leveldbjni
    )
  )

lazy val algorithm =
  project.settings(name := "algorithm-practise", settings, libraryDependencies ++= commonDependencies)

lazy val mongodb =
  project.settings(
    name := "akka-persistence-reactivemongo",
    settings,
    libraryDependencies ++= commonDependencies ++ akkaDependencies ++ Seq(
      dependencies.monocleCore,
      dependencies.monocleMacro,
      dependencies.reactivemongo,
      dependencies.scaldi,
      dependencies.leveldb,
      dependencies.leveldbjni
    )
  )

lazy val game =
  project
    .settings(
      name := "game-solve",
      settings,
      libraryDependencies ++= commonDependencies
    )
    .disablePlugins(AssemblyPlugin)
    .aggregate(sokoban, sokobanvl, sudoku)

lazy val sokoban = project
  .in(file("game/sokoban"))
  .settings(
    name := "sokoban",
    settings,
    unmanagedJars in Compile ++= (file("game/lib/") * "*.jar").classpath,
    libraryDependencies ++= commonDependencies ++ Seq(
      dependencies.scalaSwing
    )
  )
  .disablePlugins(AssemblyPlugin)

lazy val sokobanvl = project
  .in(file("game/sokobanvl"))
  .settings(
    name := "sokoban-visualiz",
    settings,
    libraryDependencies ++= commonDependencies
  )
  .dependsOn(sokoban)
  .disablePlugins(AssemblyPlugin)

lazy val sudoku = project
  .in(file("game/sudoku"))
  .settings(
    name := "sudoku",
    settings,
    unmanagedJars in Compile ++= (file("game/lib/") * "*.jar").classpath,
    libraryDependencies ++= commonDependencies
  )
  .disablePlugins(AssemblyPlugin)

// DEPENDENCIES

lazy val dependencies =
  new {
    val log4jV         = "2.7"
    val scalaLoggingV  = "3.7.2"
    val slf4jV         = "1.7.25"
    val hamcrestV      = "1.3"
    val guavaV         = "21.0"
    val commonlangV    = "3.5"
    val scalazV        = "7.2.8"
    val shapelessV     = "2.3.2"
    val scaldiV        = "0.5.8"
    val leveldbV       = "0.9"
    val leveldbJniV    = "1.8"
    val slickV         = "3.2.1"
    val catsV          = "1.0.1"
    val macroV         = "1.1.1"
    val monocleV       = "1.4.0"
    val akkaV          = "2.5.7"
    val akkaHttpV      = "10.0.10"
    val reactivemongoV = "0.12.7"
    val junitV         = "4.12"
    val scalatestV     = "3.0.4"
    val scalacheckV    = "1.13.5"
    val scalaSwingV    = "2.1.1"

    val log4jCore    = "org.apache.logging.log4j"   % "log4j-core"       % log4jV
    val log4jApi     = "org.apache.logging.log4j"   % "log4j-api"        % log4jV
    val log4jImpl    = "org.apache.logging.log4j"   % "log4j-slf4j-impl" % log4jV
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging"   % scalaLoggingV
    val slf4j        = "org.slf4j"                  % "slf4j-api"        % slf4jV

    val hamcrest     = "org.hamcrest"       % "hamcrest-all"  % hamcrestV
    val guava        = "com.google.guava"   % "guava"         % guavaV
    val commonsLang3 = "org.apache.commons" % "commons-lang3" % commonlangV
    val scalaz       = "org.scalaz"         %% "scalaz-core"  % scalazV
    val shapeless    = "com.chuusai"        %% "shapeless"    % shapelessV

    val akkaActor           = "com.typesafe.akka" %% "akka-actor"            % akkaV
    val akkaSlf4j           = "com.typesafe.akka" %% "akka-slf4j"            % akkaV
    val akkaPersistence     = "com.typesafe.akka" %% "akka-persistence"      % akkaV
    val akkaHttp            = "com.typesafe.akka" %% "akka-http"             % akkaHttpV
    val akkaHttpSprayJson   = "com.typesafe.akka" %% "akka-http-spray-json"  % akkaHttpV
    val akkaRemote          = "com.typesafe.akka" %% "akka-remote"           % akkaV
    val akkaCluster         = "com.typesafe.akka" %% "akka-cluster"          % akkaV
    val akkaClusterTools    = "com.typesafe.akka" %% "akka-cluster-tools"    % akkaV
    val akkaClusterSharding = "com.typesafe.akka" %% "akka-cluster-sharding" % akkaV
    val reactivemongo       = "org.reactivemongo" %% "reactivemongo"         % reactivemongoV

    val scaldi = "org.scaldi" %% "scaldi-akka" % scaldiV

    val leveldb    = "org.iq80.leveldb"          % "leveldb"        % leveldbV
    val leveldbjni = "org.fusesource.leveldbjni" % "leveldbjni-all" % leveldbJniV

    val slick         = "com.typesafe.slick" %% "slick"          % slickV
    val slickHikaricp = "com.typesafe.slick" %% "slick-hikaricp" % slickV
    val slickCodegen  = "com.typesafe.slick" %% "slick-codegen"  % slickV

    val mysql    = "mysql"      % "mysql-connector-java" % "6.0.6"
    val hikariCP = "com.zaxxer" % "HikariCP"             % "2.7.8"

    val catsCore    = "org.typelevel" %% "cats-core"    % catsV
    val catsKernel  = "org.typelevel" %% "cats-kernel"  % catsV
    val catsMacros  = "org.typelevel" %% "cats-macros"  % catsV
    val macroCompat = "org.typelevel" %% "macro-compat" % macroV

    val monocleCore  = "com.github.julien-truffaut" %% "monocle-core"  % monocleV
    val monocleMacro = "com.github.julien-truffaut" %% "monocle-macro" % monocleV
    val junit        = "junit"                      % "junit"          % junitV
    val scalatest    = "org.scalatest"              %% "scalatest"     % scalatestV
    val scalacheck   = "org.scalacheck"             %% "scalacheck"    % scalacheckV

    val scalaSwing = "org.scala-lang.modules" %% "scala-swing" % scalaSwingV
  }

lazy val commonDependencies = Seq(
  dependencies.log4jCore,
  dependencies.log4jApi,
  dependencies.log4jImpl,
  dependencies.scalaLogging,
  dependencies.slf4j,
  dependencies.hamcrest,
  dependencies.guava,
  dependencies.scalaz,
  dependencies.commonsLang3,
  dependencies.junit      % Test,
  dependencies.scalatest  % Test,
  dependencies.scalacheck % Test
)

lazy val akkaDependencies = Seq(
  dependencies.akkaActor,
  dependencies.akkaSlf4j,
  dependencies.akkaPersistence,
  dependencies.akkaHttp,
  dependencies.akkaHttpSprayJson,
  dependencies.akkaRemote,
  dependencies.akkaCluster,
  dependencies.akkaClusterTools,
  dependencies.akkaClusterSharding
)

lazy val slickDependencies = Seq(
  dependencies.slick,
  dependencies.slickHikaricp,
  dependencies.slickCodegen,
  dependencies.mysql,
  dependencies.hikariCP
)

lazy val catsDependencies = Seq(
  dependencies.catsCore,
  dependencies.catsKernel,
  dependencies.catsMacros,
  dependencies.macroCompat
)

// SETTINGS

lazy val settings =
  commonSettings ++
    wartremoverSettings ++
    scalafmtSettings

lazy val compilerOptions = Seq(
  "-unchecked",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-deprecation",
  "-encoding",
  "utf8"
)

lazy val commonSettings = Seq(
  scalacOptions ++= compilerOptions,
  resolvers ++= Seq(
    "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  )
)

lazy val wartremoverSettings = Seq(
  wartremoverWarnings in (Compile, compile) ++= Warts.allBut(Wart.Throw)
)

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true,
    scalafmtTestOnCompile := true,
    scalafmtVersion := "1.2.0"
  )

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", _*) => MergeStrategy.discard
    case "application.conf"       => MergeStrategy.concat
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  }
)
