package cn.galudisu.fp._1_9_scala_idioms

import com.typesafe.scalalogging.LazyLogging

/**
  * 惯用语(Idioms)
  *
  * @author galudisu
  */
object ScalaIdioms extends App with LazyLogging {

  var d1 = List(1, 2, 3, 4, 5)
  var d2 = List(11, 22, 33, 44, 55)

  val zip = (d1, d2).zipped map (_ + _)

  logger info s"$zip"

  val range = (1 to 100).map(_ * 2).filter(x => x % 3 == 0 && x % 4 == 0 && x % 5 == 0)

  logger info s"$range"

}
