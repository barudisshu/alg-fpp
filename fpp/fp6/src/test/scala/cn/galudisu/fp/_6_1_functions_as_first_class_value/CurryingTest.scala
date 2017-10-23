package cn.galudisu.fp._6_1_functions_as_first_class_value

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

import scala.annotation.tailrec

/**
  * @author galudisu
  */
class CurryingTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

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

  /** 第一类值 */
  test("Functions as first-class values") {

    val pred = (y: Int) => y < 10
    logger info s"${group.filter(pred) }"

    // 函数作为值传递，作为返回类型
    val higher: (Int => Boolean) => (Int => Boolean) = (k: Int => Boolean) => (y: Int) => y < 11
    val aFunc = higher(pred)

    logger info s"${aFunc(12) }"
    logger info s"${aFunc(10) }"
  }

  /** 范围 */
  test("Roping in a scope") {

  }

  /** 本地函数 */
  test("Local functions – hiding and biding their time") {

    def count(list: List[Int]): Int = {
      @tailrec
      def countIt(l: List[Int], acc: Int): Int = l match {
        case _ :: tail => countIt(tail, acc + 1)
        case _ => acc
      }
      countIt(list, 0) // call the real thing
    }

    logger info s"${count(group) }"

    // 本地函数有范围
    def f(n: Int) = {
      val k = (y: Int) => y < n
      k
    }
  }

  /** 占位符妙用 */
  test("The underscore – Scala's Swiss army knife") {

    logger info s"${group filter { _ > 2 } }"
    logger info s"${group reduceLeft (_ + _) }"

    val (x, y, _, _) = (7, 3, 5, 15)

    def modBy(n: Int, d: Int) = n % d

    // 偏函数 - 两个参数转换为一个参数的情况
    val funMobBy = modBy _
    val modBy2 = modBy(_: Int, 2)
  }

  /** 科里化尝试 */
  test("A taste of the curry") {

    def modBy2(n: Int)(d: Int) = n % d
    logger info s"${modBy2(10)(3) }"
    // 偏函数转换
    val p = modBy2(10) _
    logger info s"${p(2) }"

    // 直接转换
    def m(m: Int, n: Int, o: Int, p: String) = s"${m % n + o }" + p
    val px = (m _).curried
    logger info s"${px(10)(4)(2)("th") }"

    // Type inference
    def firstMatching[T](xs: List[T])(f: (T) => Boolean): Option[T] = xs
    match {
      case Nil => None
      case x :: ts => if (f(x)) Some(x) else firstMatching(ts)(f)
    }
    println(firstMatching(List(1, 2, 3, 4))((x: Int) => x < 2)) // Some(1)
    println(firstMatching(List("hi", "hello", "some", "one"))((x: String) => x.length >= 5)) // Some(hello)

    // 隐式和显式
    implicit def housewife = "Housewife"
    def f(momName: String)(implicit worksAs: String) = logger info s"Mom $momName works as $worksAs"

    f("Sheela")
    f("Nisha")("Software Engineer")

    def f2(implicit momName: String, worksAs: String) = logger info s"Mom $momName works as $worksAs"

    f2

    // Stylish blocks

  }

  test("The loan pattern"){
    import scala.io.Source
    val bufferedSource = Source.fromFile("example.txt")
    for (line <- bufferedSource.getLines) {
      println(line.reverse)
    }
    bufferedSource.close() // 1

    def autoCleanUp[T](f: Source)(handler: Source => T): T = {
      try { // 1
        handler(f) // 2
      } finally {
        println("Closing resource")
        f.close()
      }
    }

    val s1 = Source.fromFile("/home/atul/myScala/example.txt")
    val s2 = Source.fromFile("/home/atul/myScala/example.txt")
    autoCleanUp(s1) { h =>
      for (line <- h.getLines) { // 3
        println(line.reverse)
      }
    }
    autoCleanUp(s2) { h =>
      for (line <- h.getLines) { // 4
        println(line.toCharArray.length + ":" + line)
      }
    }
  }

  /**
    * 重复代码是主要的 code smell(坏味道).
    * 我们要通过 extract 和 refactoring 进行clean code.
    */
  test("Serving the curry") {

  }
}




























