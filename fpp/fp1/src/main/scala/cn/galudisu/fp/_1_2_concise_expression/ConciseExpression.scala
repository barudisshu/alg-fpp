package cn.galudisu.fp._1_2_concise_expression

import com.typesafe.scalalogging.LazyLogging

/**
  * 简洁表达式(Concise Expression)
  *
  * @author galudisu
  */
object ConciseExpression extends App with LazyLogging {

  // 不用声明类型，也不用任何new关键字。以简洁方式声明一个只读变量
  val list = List("One", "two", "three", "Four", "five")

  logger info s"$list"

  // Scala提供的`val`关键字，为我们建立了：
  // 1. 初始变量必须指定(不会有遗留未被赋值的变量)
  // 2. 变量不能被修改(代码检查)

  // 为什么要这样做？ 屏蔽内部实现、无副作用、更容易理解(understand)和表述(explain)
  def hasUpperCaseChar(s: String): Boolean = s.exists(_.isUpper)
  val ulist = list filter hasUpperCaseChar

  logger info s"$ulist"

  val glist = list filter (x => hasUpperCaseChar(x) && x.length > 3)
  logger info s"$glist"
}
