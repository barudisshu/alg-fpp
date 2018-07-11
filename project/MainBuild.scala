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
  val fp7 = Project("fp7", file("fpp/fp7"))
  val fp8 = Project("fp8", file("fpp/fp8"))
  val fp9 = Project("fp9", file("fpp/fp9"))
  val fpa = Project("fpa", file("fpp/fpa"))
  val fpb = Project("fpb", file("fpp/fpb"))
  val fpc = Project("fpc", file("fpp/fpc"))
  val fpd = Project("fpd", file("fpp/fpd"))

  val akka      = Project("akka", file("akka"))
  val algorithm = Project("algorithm", file("algorithm"))
  val mongodb   = Project("mongodb", file("mongodb"))

  val rdp = Project("rdp", file("rdp"))

  val rd1 = Project("rd1", file("rdp/rd1"))

  val frdm = Project("frdm", file("frdm"))

  val frd1 = Project("frd1", file("frdm/frd1"))

  val main: Project = Project("alg-fpp", file(".")).aggregate(
    fpp, fp1, fp2, fp3, fp4, fp5, fp6, fp7, fp8, fp9, fpa, fpb, fpc, fpd,
    akka,
    algorithm,
    mongodb,
    rdp, rd1,
    frdm, frd1
  )
}

