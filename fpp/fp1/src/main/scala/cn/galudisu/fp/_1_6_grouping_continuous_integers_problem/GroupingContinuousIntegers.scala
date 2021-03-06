package cn.galudisu.fp._1_6_grouping_continuous_integers_problem

import scala.annotation.tailrec

/**
  * 持续集成问题(Continuous Integers)
  *
  * @author galudisu
  */
object GroupingContinuousIntegers {

  // 问题描述：对于给定的一个集合，若相邻的元素彼此只相差1，则将它们分配到一个group中。

  /**
    * 递归实现，Java版实现请参考 {@link cn.galudisu.fp._1_6_grouping_continuous_integers_problem.SortNumber#groupThem}
    *
    */
  def groupNumbers(list: List[Int]) = {
    def groupThem(lst: List[Int], acc: List[Int]): List[List[Int]] = lst
    match {
      case Nil => acc.reverse :: Nil
      case x :: xs =>
        acc match {
          case Nil => groupThem(xs, x :: acc)
          case y :: ys if (x - y == 1) => groupThem(xs, x :: acc)
          case _ => acc.reverse :: groupThem(xs, x :: List())
        }
    }
    groupThem(list, List())
  }

  /**
    * 尾递归是大多数传统递归的实现方式，处理方式是TCO(tail call optimization)，即先执行计算结果，再传递给递归处理。
    * 注解 @tailrec，表示用于检测Scala是否对尾递归进行了优化，当不能优化你的函数或者方法时，编译器会在此注解的方法中抛出一个异常。
    * 尾递归实现
    */
  def groupNumbers(list: List[Int])(f: (Int, Int) => Boolean):
  List[List[Int]] = {
    @tailrec
    def groupThem(lst: List[Int], result: List[List[Int]], acc:
    List[Int]): List[List[Int]] = lst match {
      case Nil => acc.reverse :: result
      case x :: xs =>
        acc match {
          case Nil => groupThem(xs, result, x :: acc)
          case y :: ys if (x - y == 1) =>
            groupThem(xs, result, x :: acc)
          case _ =>
            groupThem(xs, acc.reverse :: result, x :: List())
        }
    }
    val r = groupThem(list, List(), List())
    r.reverse
  }
}
