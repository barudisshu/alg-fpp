package cn.galudisu.fp._5_9_cake_pattern

/**
  * @author Galudisu
  *         四月 05, 2017
  */
object ScalaDepInj extends App {

  abstract class Animal(val rating: Int, var inoculated:
  Boolean = false) {
    def name(): String
    def alreadyInoculated(): Boolean = inoculated
    def setInoculated(b: Boolean): Unit = {
      inoculated = b
    }
  }

  trait Vet {
    // 1
    def name(): String
    def inoculate(animal: Animal): Unit = {
      println("Innoculating " + animal.name)
    }
  }
  trait ChoosyVet extends Vet {
    // 2
    def alreadyInoculated(): Boolean
    abstract override def inoculate(animal: Animal): Unit = {
      if (!alreadyInoculated()) // filter out already
      // inoculated animals
        println("Innoculating " + animal.name)
    }
  }
  trait Inoculate {
    this: Animal with Vet =>
    // 3
    def setInoculated(b: Boolean)
    def giveInoculation(): Unit = {
      inoculate(this)
      setInoculated(true)
    }
    def name(): String
  }
  class Horse(rating: Int) extends Animal(rating) {
    override def name(): String = "Horse"
  }

  val h = new Horse(1) with ChoosyVet with Inoculate // 4
  h.giveInoculation() // prints message
  h.giveInoculation() // filtered out
}
