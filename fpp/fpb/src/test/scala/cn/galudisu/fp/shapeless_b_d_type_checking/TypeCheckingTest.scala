package cn.galudisu.fp.shapeless_b_d_type_checking

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

import scala.util.Try

class TypeCheckingTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

  test("type checking") {
    import shapeless.test.illTyped
    val matchedTypes = Try { assertTypeError("illTyped { \"val a: Int = 1\" }") }.isSuccess
    matchedTypes should be(true)

    val mismatchedTypes = Try { assertTypeError("illTyped { \"val a: String = 1\" }") }.isSuccess
    mismatchedTypes should be(false)
  }
}
