package cn.galudisu.fp._3_2_pattern_matching

import scala.annotation.tailrec

/**
  * @author galudisu
  */
object NthElemOfList extends App {

  def nth(list: List[Int], n: Int): Option[Int] = {
    @tailrec
    def nthElem(list: List[Int], acc: (Int, Int)): Option[Int] = list match {
      case Nil => None
      case head :: tail =>
        if (acc._1 == acc._2)
          Some(head)
        else
          nthElem(tail, (acc._1 + 1, acc._2))
    }
    nthElem(list, (0, n))
  }

  val bigList = 1 to 100000 toList

  println(nth(List(1, 2, 3, 4, 5, 6), 3).getOrElse("No such elem"))
  println(nth(List(1, 2, 3, 4, 5, 6), 300).getOrElse("No such elem"))
  println(nth(bigList, 2333).getOrElse("No such elem"))

}
