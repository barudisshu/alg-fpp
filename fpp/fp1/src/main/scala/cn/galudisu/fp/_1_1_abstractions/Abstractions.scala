package cn.galudisu.fp._1_1_abstractions

import com.typesafe.scalalogging.LazyLogging

/**
  * 抽象(Abstractions)
  *
  * @author galudisu
  */
object Abstractions extends App with LazyLogging {

  // 什么是抽象？为什么如此重要？要理解这个问题，
  // 我们以开门为例：先把小木块塞进矩形小孔，然后打开门。这里我们不用关心门是由什么构造的、它是什么锁、这些细节虽然重要，但对开门这个动作没有关系。

  // selectively ignorant of the details at times，有针对性地忽略细节；选择性的忽略便是抽象。
  // 为什么要这样做？便于密集处理 --> Compact Manager；下面是一个字符串的密集操作

  var map = "hello world".toList.filter(_.isLetter).groupBy(x => x).map { y => y._1.toUpper -> y._2.size }
  logger info s"$map"

  // 带来的好处是：简洁、短小精悍、优雅/无副作用，`Type Less Do More`思想，对项目来说是抽象的、可复用的

}
