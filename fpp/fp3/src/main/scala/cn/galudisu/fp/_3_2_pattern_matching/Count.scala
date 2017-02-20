package cn.galudisu.fp._3_2_pattern_matching

import scala.annotation.tailrec

/**
  * @author galudisu
  */
object Count extends App {

  /**
    * 递归实现
    */
  def count(list: List[Int]): Int = list match {
    case Nil => 0
    case head :: tail => head + count(tail)
  }

  /**
    * 尾递归实现
    */
  def tailCount(list: List[Int]): Int = {
    @tailrec
    def countIt(l: List[Int], acc: Int): Int = l match {
      case Nil => acc
      case head :: tail => countIt(tail, acc + head)
    }
    countIt(list, 0)
  }

  val l = List(1, 2, 3, 4, 5)
  println(count(l))
  println(tailCount(l))

}
