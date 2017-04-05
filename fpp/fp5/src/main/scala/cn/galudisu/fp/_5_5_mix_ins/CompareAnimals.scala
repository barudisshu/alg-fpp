package cn.galudisu.fp._5_5_mix_ins

/**
  * Scala trait混入
  *
  * @author Galudisu
  *         四月 05, 2017
  */
object CompareAnimals extends App {

  trait Walks {
    def name: String
    def walk(): Unit = println(name + " is having a stroll now")
  }

  trait GoodsMover {
    def name: String
    def moveGoods(): Unit = println(name + " busy moving heavy stuff")
  }

  abstract class Animal(val rating: Int) extends Ordered[Animal]

  class Horse(rating: Int) extends Animal(rating) with Walks {
    override def name: String = "Horse"
    override def compare(that: Animal): Int = rating - that.rating
  }
  class Donkey(rating: Int) extends Animal(rating) with Walks with GoodsMover {
    override def name: String = "Donkey"
    override def compare(that: Animal): Int = rating - that.rating
  }
  val horse  = new Horse(1)
  val donkey = new Donkey(2)
  if (horse < donkey) {
    println("Donkeys are more useful than horses - breed Donkeys")
  } else if (horse > donkey) {
    println("Horses are more useful than donkeys - breed Horses")
  }

}
