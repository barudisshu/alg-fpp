package cn.galudisu.fp._2_1_singletons

/**
  * @author galudisu
  */
class Single { // Companion class
  def m(): Unit = {
    println("class")
  }
}

object Single { // Companion object
  def m(): Unit = {
    println("companion")
  }
}
