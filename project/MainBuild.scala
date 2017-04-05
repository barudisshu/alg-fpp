import sbt._

/**
  * SBT构建入口
  */
object MainBuild extends Build {

  val fpp = Project("fpp", file("fpp"))
  val fp1 = Project("fp1", file("fpp/fp1"))
  val fp2 = Project("fp2", file("fpp/fp2"))
  val fp3 = Project("fp3", file("fpp/fp3"))
  val fp4 = Project("fp4", file("fpp/fp4"))
  val fp5 = Project("fp5", file("fpp/fp5"))
  val fp6 = Project("fp6", file("fpp/fp6"))

  val akka      = Project("akka", file("akka"))
  val algorithm = Project("algorithm", file("algorithm"))

  val main: Project = Project("alg-fpp", file(".")).aggregate(
    fpp, fp1, fp2, fp3, fp4, fp5, fp6,
    akka,
    algorithm
  )
}

