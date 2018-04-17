package cn.galudisu.fp.shapeless_b_c_cast

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}
import shapeless._

class SafeCastTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

  test("cast iterator") {
    import syntax.typeable._

    val l: Any = List(Vector("foo", "bar", "baz"), Vector("wibble"))

    l.cast[List[Vector[String]]] should be(Some(List(Vector("foo", "bar", "baz"), Vector("wibble"))))
    l.cast[List[Vector[Int]]] should be(None)
    l.cast[List[List[String]]] should be(None)
  }

  test("extractor pattern matches") {
    val `List[String]` = TypeCase[List[String]]
    val `List[Int]` = TypeCase[List[Int]]
    val l = List(1, 2, 3)

    val result = (l: Any) match {
      case `List[String]`(List(s, _*)) ⇒ s.length
      case `List[Int]`(List(i, _*)) ⇒ i + 1
    }

    result should be(2)
  }
}
