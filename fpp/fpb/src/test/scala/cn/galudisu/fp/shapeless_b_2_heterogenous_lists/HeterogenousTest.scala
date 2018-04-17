package cn.galudisu.fp.shapeless_b_2_heterogenous_lists

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}
import shapeless._

class HeterogenousTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

  info("Starting...")

  test("no shape list map") {
    val sets = Set(1) :: Set("foo") :: HNil
    val opts = sets map choose

    opts should be(Some(1) :: Some("foo") :: HNil)
  }

  test("on shape list flatMap") {
    import poly.identity

    val l = (23 :: "foo" :: HNil) :: HNil :: (true :: HNil) :: HNil
    l flatMap identity should be(23 :: "foo" :: true :: HNil)
  }

  test("on shape list fold") {

    val l = 23 :: "foo" :: (13, "wibble") :: HNil
    l.foldLeft(0)(addSize) should be(11)
  }

  test("zipper for update") {
    import syntax.zipper._

    val l = 1 :: "foo" :: 3.0 :: HNil
    l.toZipper.right.put(("wibble", 45)).reify should be(1 :: ("wibble", 45) :: 3.0 :: HNil)

    l.toZipper.right.delete.reify should be(1 :: 3.0 :: HNil)
  }

  test("covariant") {
    import CovariantHelper._

    import scala.reflect.runtime.universe._
    implicitly[TypeTag[APAP]].tpe.typeConstructor <:< typeOf[FFFF] should be(true)
  }

  test("convert it to HList") {
    import CovariantHelper._

    apap.isInstanceOf[FFFF] should be(true)
    apap.unify.isInstanceOf[FFFF] should be(true)
  }

  test("toList as Scala") {
    import CovariantHelper._

    apap.toList should be(List(Apple(), Pear(), Apple(), Pear()))
  }

  test("to instance") {
    import CovariantHelper._
    import syntax.typeable._

    val ffff: FFFF = apap.unify
    val precise: Option[APAP] = ffff.cast[APAP]

    precise should be(Some(Apple() :: Pear() :: Apple() :: Pear() :: HNil))
  }
}

