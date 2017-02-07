package cn.galudisu.fp._1_4_immutable

import com.typesafe.scalalogging.LazyLogging

/**
  * 不可变性(Immutable)
  *
  * @author galudisu
  */
object Immutable extends App with LazyLogging {

  // 不可变体现在Scala的很多方面，下面以case class 为例
  case class FullName(firstName: String, lastName: String)
  val name = FullName("Bertie", "Wooster")

  logger info s"$name"

  // 如果一个类的前缀是case关键字，则它会自动完成下面的工作:
  // ① 所有参数前缀用val修饰，使它们作为public成员。但仍然不能对其进行直接方法，若要访问，需要通过访问器(accessors)。
  // ② 根据参数自动实现equals和hashCode方法。
  // ③ 编译器会自动实现toString方法，并返回类名和参数。
  // ④ 所有case class都有一个copy方法用于该类实例的复制。
  // ⑤ 伴生对象(companion object)被创建并提供apply方法，并且该方法参数和该case class参数一致。
  // ⑥ 编译器会添加一个unapply方法，该方法将用于正则匹配中的类名提取器中。
  // ⑦ 默认的实现将提供了序列化。

  // 因此，尝试修改`name`的属性将出现编译错误
  name.firstName = "Mrs. Bertie"

}
