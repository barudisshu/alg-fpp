package cn.galudisu.fp.shapeless_b_1_polymorphic

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}
import shapeless.Poly1
import shapeless.PolyDefns.~>

import scala.language.higherKinds

class ShapelessTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

  info("Starting... ")

  object choose extends (Seq ~> Option) {
    override def apply[T](f: Seq[T]): Option[T] = f.headOption
  }

  test("no type specific cases") {
    choose(Seq(1, 2, 3)) should be(Some(1))
    choose(Seq('a', 'b', 'c')) should be(Some('a'))
  }

  def pairApply(f: Seq ~> Option) = (f(Seq(1, 2, 3)), f(Seq('a', 'b', 'c')))

  test("passed as arguments to functions or methods") {
    pairApply(choose) should be(Some(1), Some('a'))
  }

  test("interoperable with other high-kinded function") {
    List(Seq(1, 3, 5), Seq(2, 4, 6)).map(choose(_)) should be(List(Some(1), Some(2)))
  }

  test("type-specific") {
    object size extends Poly1 {
      implicit def caseInt = at[Int](x ⇒ 1)
      implicit def caseString = at[String](_.length)
      implicit def caseTuple[T, U](implicit st: Case.Aux[T, Int], su: Case.Aux[U, Int]) =
        at[(T, U)](t ⇒ size(t._1) + size(t._2))
    }

    size(23) should be (1)
    size("foo") should be (3)
    size((23, "foo")) should be (4)
    size(((23, "foo"), 13)) should be (5)
  }
}


































