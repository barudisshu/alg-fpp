package cn.galudisu.fp.shapeless_b_3_tuples

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}
import shapeless._
import syntax.std.tuple._

class TuplesTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

  info("Starting...")

  val xs: (Int, String, Boolean) = (23, "foo", true)

  /**
    * head
    * tail
    * drop
    * take
    * split
    * prepend
    * concatenate
    * map
    * flatMap
    * fold
    */
  test("hlist-style standard scala tuple") {

    xs.head should be(23)
    xs.tail should be("foo", true)
    xs.drop(2) should be(Tuple1(true))
  }

  test("to HList") {
    xs.productElements should be(23 :: "foo" :: true :: HNil)
  }

  test("toList") {
    xs.toList should be(List(23, "foo", true))
  }

  test("zipper") {
    import syntax.zipper._


    val l = (23, ("foo", true), 2.0).toZipper.right.down.put("bar").root.reify
    l should be((23, ("bar", true), 2.0))
  }
}
