package cn.galudisu.fp._2_1_singletons

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

/**
  * @author galudisu
  */
class SingletonTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

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
    * 伴生对象定义了一个`apply()`方法，Scala编译器在遇到 class()时，会调用该方法
    * Singleton(arg1, arg2, ..., argN) // 语法糖
    * Singleton.apply(arg1, arg2, ..., argN)
    */
  test("The apply() factory method") {
    val p = Map("one" -> 1, "two" -> 2)
    val q = Map.apply("one" -> 1, "two" -> 2)
    assertResult(true)(p equals q)

    class C(x: Int) {
      def print(): Unit = println(x)
    }

    object C {
      def apply(x: Int): C = {
        new C(x)
      }
    }

    val k = C(9)
    k.print()

    // apply 也适用于方法
    val f = (l: List[Int]) => l.map(_ * 2)
    assertResult(true)(f(ls) equals f.apply(ls))
  }

  test("Implicit conversion") {
    val p = "Hello World & Good Morning!".partition(x => x.isUpper || x.isLower)
    logger info s"$p"
  }
}
