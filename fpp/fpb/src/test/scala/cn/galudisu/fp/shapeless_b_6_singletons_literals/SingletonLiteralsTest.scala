package cn.galudisu.fp.shapeless_b_6_singletons_literals

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

class SingletonLiteralsTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

  import shapeless._
  import syntax.std.tuple._
  import syntax.singleton._

  val hlist = 23 :: "foo" :: true :: HNil
  val tuple = (23, "foo", true)

  test("single") {

    hlist(1) should be("foo")
    tuple(1) should be("foo")

    Witness(23).value == 23 should be(true)
    Witness("foo").value == "foo" should be(true)
  }

  val (wTrue, wFalse) = (Witness(true), Witness(false))

  type True = wTrue.T
  type False = wFalse.T

  trait Select[B] { type Out }

  implicit val selInt = new Select[True] { type Out = Int }
  implicit val selString = new Select[False] { type Out = String }

  def select(b: WitnessWith[Select])(t: b.instance.Out) = t


  test("witness") {
    select(true)(23) should be(23)

    //select(true)("foo")
    //error: type mismatch;
    // found   : String("foo")
    // required: Int
    //              select(true)("foo")
    //                           ^

    //select(false)(23)
    // error: type mismatch;
    //found   : Int(23)
    //required: String

    select(false)("foo") should be("foo")
  }
}
