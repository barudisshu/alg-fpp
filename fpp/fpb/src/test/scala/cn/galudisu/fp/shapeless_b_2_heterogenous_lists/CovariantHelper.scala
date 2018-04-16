package cn.galudisu.fp.shapeless_b_2_heterogenous_lists

import shapeless._

object CovariantHelper {

  trait Fruit
  case class Apple() extends Fruit
  case class Pear() extends Fruit

  type FFFF = Fruit :: Fruit :: Fruit :: Fruit :: HNil
  type APAP = Apple :: Pear :: Apple :: Pear :: HNil

  val a: Apple = Apple()
  val p: Pear  = Pear()

  val apap: APAP = a :: p :: a :: p :: HNil

}
