package cn.galudisu.fp._1_6_grouping_continuous_integers_problem

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

/**
  * @author galudisu
  */
class GroupingContinuousIntegersTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

  info("Starting... ")

  /*------------
  |   变量部分
  ------------*/
  private var shrink: Double     = _
  private var ls    : List[Int]  = _
  private var as    : Array[Int] = _
  private var group : List[Int]  = _

  before {

    shrink = 0.8
    ls = List(1, 2, 3, 4, 5)
    as = Array(1, 2, 3, 4, 5)
    group = List(1, 2, 3, 4, 5, 9, 11, 20, 21, 22)
  }

  test("Sorted list on grouping numbers") {
    val groups: List[List[Int]] = GroupingContinuousIntegers.groupNumbers(group)
    assertResult(4)(groups.size)
    assertResult(List(1, 2, 3, 4, 5))(groups.head)
    assertResult(List(9))(groups(1))
    assertResult(List(11))(groups(2))
    assertResult(List(20, 21, 22))(groups.last)
  }

}
