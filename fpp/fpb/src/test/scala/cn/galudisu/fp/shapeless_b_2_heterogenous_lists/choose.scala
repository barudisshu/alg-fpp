package cn.galudisu.fp.shapeless_b_2_heterogenous_lists

import shapeless.PolyDefns.~>

object choose extends (Set ~> Option) {
  override def apply[T](f: Set[T]): Option[T] = f.headOption
}
