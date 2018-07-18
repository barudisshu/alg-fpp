package cn.galudisu.typecheck

import cn.galudisu.typecheck.BoilerplateMacros._
import org.scalatest.{FunSuite, MustMatchers}

class BoilerplateMacrosTest extends FunSuite with MustMatchers {

  test("DTO_1 has its own type") {
    val dto = DTO_2(field1 = "hello world", field2 = "")
    println(s"==> ${dto.field1}")
    succeed
  }

}
