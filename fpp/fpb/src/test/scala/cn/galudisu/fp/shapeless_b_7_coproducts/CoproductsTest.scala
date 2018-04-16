package cn.galudisu.fp.shapeless_b_7_coproducts

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

class CoproductsTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

  import shapeless._

  type ISB = Int :+: String :+: Boolean :+: CNil
  val isb = Coproduct[ISB]("foo")

  test("coproduct") {
    isb.select[Int] should be(None)
    isb.select[String] should be(Some("foo"))
  }

  object sizeM extends Poly1 {
    implicit def caseInt = at[Int](i => (i, i))
    implicit def caseString = at[String](s => (s, s.length))
    implicit def caseBoolean = at[Boolean](b => (b, 1))
  }

  test("coproduct support mapping") {
    val m = isb map sizeM

    m.select[(String, Int)] should be(Some(("foo", 3)))
  }

  test("union") {
    import record._, union._, syntax.singleton._

    type U = Union.`'i -> Int, 's -> String, 'b -> Boolean`.T

    val u = Coproduct[U]('s ->> "foo") // Inject a String into the union at label 's

    u.get('i) should be(None)
    u.get('s) should be(Some("foo"))
    u.get('b) should be(None)
  }
}
