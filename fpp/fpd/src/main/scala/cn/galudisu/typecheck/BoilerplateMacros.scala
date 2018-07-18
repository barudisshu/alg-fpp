package cn.galudisu.typecheck

import scala.language.higherKinds

/**
  * 类型安全的case-class 样板代码
  */
object BoilerplateMacros {

  case class DTO_0()
  case class DTO_1[A](field1: A)
  case class DTO_2[A1, A2](field1: A1, field2: A2)
}

