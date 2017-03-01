package cn.galudisu.alg._0_2

import scala.{BigDecimal => L}

/**
  * @author galudisu
  */
object Linear extends App {

  /**
    * 根据回归方程，实现回归函数
    * 回归方程为y = a + bx
    */
  def line(f: (Map[L, L], L) => L, p: Map[L, L], x: L): L = {
    f(p, x)
  }

  /**
    * 输入散点内容，求取回归方程
    */
  def qa(param: Map[L, L], x: L): L = {

    var xl = List[L]()
    var yl = List[L]()

    param foreach { t => {
      xl ::= t._1
      yl ::= t._2
    }
    }

    // 求平均数
    val xp = xl.sum / xl.length
    val yp = yl.sum / yl.length

    // 求和
    var fz: L = 0
    var fm: L = 0
    param foreach { t => {
      fz += (t._1 - xp) * (t._2 - yp)
      fm += ((t._1 - xp) * (t._1 - xp))
    }
    }

    // 求参数b
    val b = fz / fm

    // 求参数a
    val a = yp - b * xp

    a + b * x
  }

  val p: Map[L, L] = Map(
    L(60) -> L(63.6),
    L(62) -> L(65.2),
    L(64) -> L(66),
    L(65) -> L(65.5),
    L(66) -> L(66.9),
    L(67) -> L(67.1),
    L(68) -> L(67.4),
    L(70) -> L(68.3),
    L(72) -> L(70.1),
    L(74) -> L(70))

  // 测试
  println(line(qa, p, 78))


}
