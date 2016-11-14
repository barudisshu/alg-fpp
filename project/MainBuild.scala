import com.earldouglas.xwp.JettyPlugin
import sbt._
import Keys._

/**
  * SBT构建入口
  */
object MainBuild extends Build {

  val fpp = Project("fpp",file("fpp"))
  val akka = Project("akka",file("akka"))
  val algorithm = Project("algorithm", file("algorithm"))

  val main = Project("alg-fpp", file(".")).aggregate(
    fpp, akka, algorithm
  )
}

