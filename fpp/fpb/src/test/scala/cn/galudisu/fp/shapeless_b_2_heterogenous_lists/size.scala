package cn.galudisu.fp.shapeless_b_2_heterogenous_lists

import shapeless.Poly1

object size extends Poly1 {
  implicit def caseInt = at[Int](x ⇒ 1)
  implicit def caseString = at[String](_.length)
  implicit def caseTuple[T, U](implicit st: Case.Aux[T, Int], su: Case.Aux[U, Int]) =
    at[(T, U)](t ⇒ size(t._1) + size(t._2))
}
