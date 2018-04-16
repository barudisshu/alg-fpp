package cn.galudisu.fp.shapeless_b_5_hmap

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

class HMapTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

  import shapeless._

  class BiMapIS[K, V]
  implicit val intToString = new BiMapIS[Int, String]
  implicit val stringToInt = new BiMapIS[String, Int]

  val hm = HMap[BiMapIS](23 -> "foo", "bar" -> 13)
  //val hm2 = HMap[BiMapIS](23 -> "foo", 23 -> 13)   // Does not compile

  test("relation for K/V") {
    hm.get(23) should be(Some("foo"))
    hm.get("bar") should be(Some(13))
  }

  test("monomorphic scala map") {
    import hm._     // for implicit
    val l = 23 :: "bar" :: HNil
    val m = l map hm
    m should be("foo" :: 13 :: HNil)
  }

}
