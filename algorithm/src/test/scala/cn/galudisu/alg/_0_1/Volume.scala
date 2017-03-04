package cn.galudisu.alg._0_1

/**
  *
  * 问题分析：
  * 1. 形成积水的条件，高-低-高，所以
  *
  * @author galudisu
  */
object Volume extends App {

  /**
    * 我们可以想象成下了一场雨，雨水填充了所有低洼，因此分析得出，不管给出什么数组
    * 最终都形成一个两边低，中间高的“正态分布”抛物线，所以我们从两边开始检索
    * 1. 从两边开始检索，分别设置为i和j，分别标识为最高值
    * 2. 检索进行中，遇到比其低的值，计算差
    */
  def volume(list: List[Int]): Int = {

    object Work extends Enumeration {
      val head, tail = Value
    }
    import Work._

    // 总面积
    var total = 0
    var i = 0
    var j = list.length - 1
    var i_max = list.head
    var j_max = list.last

    var who_walk = head

    // 数组长度必须大于3
    if (list.length >= 3) {

      while (i != j) {

        if (who_walk == head) {
          if (i_max < list(i)) {
            i_max = list(i)
            if (i_max > j_max) {
              who_walk = tail
            }
          } else {
            total += i_max - list(i)
          }
          i += 1
        }

        if (who_walk == tail) {
          if (j_max < list(j)) {
            j_max = list(j)
            if (i_max <= j_max) {
              who_walk = head
            }
          } else {
            total += j_max - list(j)
          }
          j += -1
        }
      }
    }

    total
  }

  val xs = List(1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
  println(volume(xs))

}
