package cn.galudisu.fp._1_8_one_liner_shockers

import com.typesafe.scalalogging.LazyLogging

import scalaz.syntax.std.list._

/**
  * 一行程序代码(One-liner Shockers)：函数组合
  *
  * @author galudisu
  */
object OneLinerShockers extends App with LazyLogging {

  // 如果了解Linux，一定知道管道技术： seq 1 100 | paste -s -d '*' | bc
  // Scala 借鉴Unix 管道技术：

  // Write pure functions that do one thing and do it well.
  // Write functions that can compose with other functions.

  // 我们以Servlet在Scala中的一个转换库为例 `Scalaz`
  var groups = List(1, 2, 3, 4, 6, 8, 9) groupWhen ((x, y) => y - x == 1)

  logger info s"$groups"

  // 仅需要一行代码便可以进行分组
}
