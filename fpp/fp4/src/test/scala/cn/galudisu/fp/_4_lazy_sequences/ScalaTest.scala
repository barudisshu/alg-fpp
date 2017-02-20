package cn.galudisu.fp._4_lazy_sequences

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

/**
  * @author galudisu
  */
class ScalaTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

  info("Starting...")

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

  /**
    * lazy val声明的变量，仅当其调用时，才执行内部代码
    */
  test("lazy val") {
    lazy val p = {
      println("Initializing")
      9
    }

    println(p) // 首次将得到输出信息

    println(p)
  }


}
