package cn.galudisu.fp._5_7_stackable_modifications

/**
  * 灵活的修改继承实现
  *
  * @author Galudisu
  *         四月 05, 2017
  */
object Stackable extends App {

  trait Walks {
    def name: String
    def walk(): Unit = println(name + " is having a stroll now")
  }

  trait GoodsMover {
    def name: String
    def moveGoods(): Unit = println(name + " busy moving heavy stuff")
  }

  /** 抽象 */
  abstract class Animal(val rating: Int) {
    def giveInoculation(): Unit
    // abstract method
    def alreadyInoculated(): Boolean // abstract method
  }

  trait FilterOutAlreadyInoculated extends Animal {
    abstract override def giveInoculation(): Unit = // 1
      if (!alreadyInoculated())
        super.giveInoculation()
  }

  trait NullifyFiltering extends Animal {
    override def alreadyInoculated() = false
  }

  class Horse(rating: Int, var inoculated: Boolean = false) extends Animal(rating) with Walks with Ordered[Animal] {
    override def giveInoculation(): Unit = {
      println(name + " Getting inoculated")
      inoculated = true
    }
    override def alreadyInoculated(): Boolean = inoculated
    override def name: String = "Horse"
    override def compare(that: Animal): Int = rating - that.rating
  }

  val horse = new Horse(1) with NullifyFiltering with FilterOutAlreadyInoculated

  horse.giveInoculation()

}
