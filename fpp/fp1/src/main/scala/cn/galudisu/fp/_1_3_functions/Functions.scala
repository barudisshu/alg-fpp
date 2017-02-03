package cn.galudisu.fp._1_3_functions

import com.typesafe.scalalogging.LazyLogging

/**
  * 函数(Functions)
  *
  * @author galudisu
  */
object Functions extends App with LazyLogging {

  // 认识闭包(closure：引用了自由变量的函数，p被称为开放项，addP被称为关闭项)，闭包都是非纯函数
  var p    = 1
  val addP = (x: Int, y: Int) => {
    p += 1
    x + y + p
  }

  logger info s"${addP(3, 4) } -> ${addP(3, 4) }"

  // 函数：单例的引用透明(referentially transparent)表达式，不包含自由变量，这个表达式构成封闭术语
  // 引用透明：对于每次相同的输入，无论进行多少次，都能得到相同的结果
  // 函数和方法的区别：函数有返回值；方法可有可无
  // 纯函数：无副作用(side effect free)的函数
  // 函数有很多种，包括有高阶函数、偏函数、curry函数、monads、以及函数的递归实现(或尾递归)等

  val addThem = (x: Int, y: Int) => {
    x + y + 1
  }

  logger info s"${addThem(1, 1) + addThem(3, 4) }"

}
