import sbt._

/**
  * SBT构建入口
  */
object MainBuild extends Build {

  val fpp = Project("fpp", file("fpp"))
  val fp1 = Project("fp1", file("fpp/fp1"))
  val fp2 = Project("fp2", file("fpp/fp2"))
  val fp3 = Project("fp3", file("fpp/fp3"))

  val akka      = Project("akka", file("akka"))
  val algorithm = Project("algorithm", file("algorithm"))

  val main = Project("alg-fpp", file(".")).aggregate(
    fpp, fp1, fp2, fp3,
    akka,
    algorithm
  )
}

