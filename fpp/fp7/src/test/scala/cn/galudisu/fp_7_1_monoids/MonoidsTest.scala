package cn.galudisu.fp_7_1_monoids

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

class MonoidsTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

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

  /**
    * 代数数据类型(ADT)
    */
  test("Algebra Data Type") {

    sealed trait Command
    case class Request[M](op: M) extends Command
    case class Response[M](op: M) extends Command

  }

  /**
    * A monoid consists of the following:
    *
    * - 类型A
    * - 一个二元操作符，使得任意的 x, y, z ∈ A，满足 op(op(x,y), z) == op(x, op(y,z))
    * - zero, satisfy op(x, zero) == x and op(zero, x) == x for any x: A
    */
  test("Monoids") {

    trait Monoid[A] {
      def op(a1: A, a2: A): A
      def zero: A
    }

    val stringMonoid = new Monoid[String] {
      override def op(a1: String, a2: String) = a1 + a2
      override def zero = ""
    }

    def listMonoid[A] = new Monoid[List[A]] {
      override def op(a1: List[A], a2: List[A]) = a1 ++ a2
      override def zero = Nil
    }

    logger info s"${stringMonoid.op("A", "B") }"
    logger info s"${listMonoid.op(List(1), 0 :: Nil) }"

    def associativeLaw[A](x: A, y: A, z: A)(implicit m: Monoid[A]): Boolean = m.op(x, m.op(y, z)) == m.op(m.op(x, y), z)

    def identityLaw[A](x: A)(implicit m: Monoid[A]): Boolean = {
      (m.op(x, m.zero) == x) &&
        (m.op(m.zero, x) == x)
    }


  }

  test("taste") {

    trait Monoid[A] {
      def append(a1: A, a2: A): A
      def zero: A
    }

    implicit val intMonoid:Monoid[Int] = new Monoid[Int] {
      def append(a: Int, b: Int):Int = a + b
      def zero = 0
    }

    def sum[A](ts: List[A])(implicit m: Monoid[A]): A = ts.foldLeft(m.zero)(m.append)

    logger info s"${sum(List(1,2,3))}"
  }

}
