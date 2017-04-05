package cn.galudisu.fp._5_4_scala_rich_interfaces

/**
  *
  * Scala提供了Trait，解决了编程中的多重继承问题
  *
  * @author Galudisu
  *         四月 05, 2017
  */
object RichInterfaces extends App {

  /** 名称 */
  trait NameIt {
    def name(): String
  }

  /** 走路 */
  trait Walks extends NameIt {
    def walk(): Unit = println(name() + " is having a troll  now")
  }

  /** 拉货 */
  trait GoodsMover extends NameIt {
    def moveGoods(): Unit = println(name() + " busy move heavy stuff")
  }

  /** 马 */
  class Horse extends Walks {
    override def name(): String = "Horse"
  }

  /** 驴 */
  class Donkey extends Walks with GoodsMover {
    override def name(): String = "Donkey"
  }

  val horse  = new Horse
  val donkey = new Donkey

  horse.walk()
  donkey.walk()
  donkey.moveGoods()

  /** 骡 */
  val mule = new Walks with GoodsMover {
    override def name(): String = "Mule"
  }

  mule.walk()
  mule.moveGoods()

}
