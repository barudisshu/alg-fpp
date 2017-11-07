package cn.galudisu.fp.fp_9_1_scalaz

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

import scala.concurrent.Future
import scala.language.higherKinds
import scalaz.Scalaz._
import scalaz._

class ScalazTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

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

  test("pimp my library") {

    val boolT = 6 < 10
    val boolF = 6 > 10

    boolT.option("corrie") shouldBe Some("corrie")
    boolF.option("emmerdale") shouldBe None
  }

  test("parseXXX on String types") {

    "6".parseInt shouldBe Success(6)
    "corrie".parseBoolean match {
      case Success(x) => fail
      case Failure(e) => succeed
    }
  }

  test("the return of the ternary operator") {
    val boolT = 6 < 10
    assertResult("corrie")(boolT ? "corrie" | "emmerdale")
  }

  test("`.allPairs` on List types") {
    val tuples = List(1, 2, 3, 4).allPairs
    assertResult(List((1, 2), (2, 3), (3, 4), (1, 3), (2, 4), (1, 4)))(tuples)
  }

  test("`.some` on everything") {
    val corrie = "corrie".some
    assertResult(corrie)(Some("corrie"))

    case class Money(amount: Int, currency: String)
    Money(3, "GBP").some
  }

  test("`.fold` on Option types") {
    val corrie = "corrie".some
    val len = corrie.fold(-1)(str => str.length)
    assertResult(len)(6)
  }

  test("typeclasses: a simple example") {

    trait Ord[T] {
      def compare(a: T, b: T): Boolean
    }

    object intOrd extends Ord[Int] {
      override def compare(a: Int, b: Int): Boolean = a <= b
    }

    def sort[T](xs: List[T])(ord: Ord[T]): List[T] = {
      def _sort(o: List[T], g: List[T]): List[T] = {
        o match {
          case Nil => g
          case head :: tail =>
            if (g.foldLeft(false)((_, ele) => ord.compare(head, ele)))
              _sort(tail, head :: g)
            else
              _sort(tail, g :+ head)
        }
      }

      _sort(xs, List.empty)
    }

    val s = sort(List(3, 2, 4, 1))(intOrd)
    assertResult(s)(List(1, 2, 3, 4))

  }

  test("`Functor` is something that can be mapped") {

    def addSix[F[_]](toAdd: F[Int])(implicit mapper: Functor[F]): F[Int] = {
      mapper.map(toAdd)(_ + 6)
    }

    assertResult(addSix(10.some))(Some(16))
    assertResult(addSix(List(10)))(List(16))
    assertResult(addSix(List(10, 11, 12, 13)))(List(16, 17, 18, 19))
  }

  test("monad definition in scalaz is effectively") {

  }
}


































