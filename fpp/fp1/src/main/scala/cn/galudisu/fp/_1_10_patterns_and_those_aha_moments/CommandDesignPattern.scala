package cn.galudisu.fp._1_10_patterns_and_those_aha_moments

import com.typesafe.scalalogging.LazyLogging

/**
  * 命令设计模式(Command Design Pattern)
  *
  * @author galudisu
  */
object CommandDesignPattern extends App with LazyLogging {

  case class Command(f: Int => Unit)
  def invokeIt(i: Int, c: Command): Unit = c.f(i)
  def cmd1 = Command(x => logger info s"**$x**")
  def cmd2 = Command(x => logger info s"++++$x++++")

  invokeIt(3, cmd1)
  invokeIt(5, cmd2)
}
