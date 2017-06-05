package cn.galudisu.fp._5_10_sealed_traits

/**
  * Sealed Traits ，密封trait，表示仅能在同一个文件中继承
  *
  * @author Galudisu
  *         四月 05, 2017
  */
object SealedTraits extends App {

  trait Num

  final case class One() extends Num
  final case class Two() extends Num

  def m(p: Num): Unit = p match {
    case _: One => println("1")
  }

  m(One())
}
