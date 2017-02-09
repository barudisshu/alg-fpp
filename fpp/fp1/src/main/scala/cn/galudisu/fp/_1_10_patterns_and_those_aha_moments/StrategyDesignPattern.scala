package cn.galudisu.fp._1_10_patterns_and_those_aha_moments

import com.typesafe.scalalogging.LazyLogging

/**
  * 策略设计模式(Strategy Design Pattern)
  *
  * @author galudisu
  */
object StrategyDesignPattern extends App with LazyLogging {

  // 策略帮助我们定义一系列算法，封装每个步骤，以及选择合适的解决问题。
  // 和Java的不同，Scala直接把策略作为函数传入，我们不用重复去new一个策略

  val strategy = List(3, 7, 5, 2).sortWith(_ < _)
  logger info s"$strategy"

  // 开闭原则(Open/Closed principle, OCP)

  def addThem(a: Int, b: Int) = a + b
  def subtractThem(a: Int, b: Int) = a - b
  def multiplyThem(a: Int, b: Int) = a * b

  def execute(f: (Int, Int) => Int, a: Int, b: Int) = f(a, b)

  logger info s"${execute(addThem, 3, 4) }"
  logger info s"${execute(subtractThem, 3, 4) }"
  logger info s"${execute(multiplyThem, 3, 4) }"

  val divideThem = (x: Int, y: Int) => x / y
  logger info s"Divide: ${execute(divideThem, 11, 5) }"
}
