package cn.galudisu.fp._1_5_referential_transparency

import com.typesafe.scalalogging.LazyLogging

import scala.collection.mutable.ListBuffer

/**
  * 引用透明(referential transparency)
  *
  * @author Administrator
  */
object ReferentialTransparency extends App with LazyLogging {

  // 要理解引用透明，先思考下面描述：
  // Let me tell you a bit about India's capital, New Delhi(新德里). The Indian capital houses the
  // Indian Parliament. The Indian capital is also home to Gali Paranthe Wali(新德里著名烤饼店), where you
  // get to eat the famous parathas(印度烤饼).

  // 我们可以换一种方式阐述：
  // Let me tell you a bit about India's capital, New Delhi. New Delhi houses the Indian
  // Parliament. New Delhi is also home to Gali Paranthe Wali, where you get to eat the
  // famous parathas.

  // 因此，引用透明的真正含义在于：

  def f1(x: Int, y: Int) = x * y
  def f(x: Int, y: Int, p: Int, q: Int) = x * y + p * q

  logger info s"${f(2, 3, 4, 5) }"

  // f 方法可以替代为：引用透明
  def f_(x: Int, y: Int, p: Int, q: Int) = f1(x, y) + f1(p, q)

  // 哪类方法不是引用透明的？
  val lb = ListBuffer(1, 2)
  val x  = lb += 9
  logger info s"${x.mkString("-") }"
  logger info s"${x.mkString("-") }"

  // 但如果改为，则得到不相同的结果；因为定义在`ListBuffer`中的`+=`方法不是纯函数(pure function)
  logger info s"${(lb += 9).mkString("-") }"
  logger info s"${(lb += 9).mkString("-") }"


}
