package cn.galudisu.fp.shapeless_b_2_heterogenous_lists

import shapeless.Poly2

object addSize extends Poly2 {
  implicit def default[T](implicit st: size.Case.Aux[T, Int]) =
    at[Int, T] { (acc, t) => acc + size(t) }
}

