package cn.galudisu.alg._0_3

import scala.annotation.tailrec

/**
  * 0-1 背包问题
  * 问题描述：我们有以下物品。每个物品有重量(weight)和价值(worth value)
  *
  *
  * [1kg, $1], [3kg, $8], [5kg, $18], [6kg, $22], [7kg, $28]
  *
  * 我们希望将这些物品放进背包里面。然后，它有重量限制：
  *
  * [[ <= 11 kg ]]
  *
  * 因此，需要选择将物品放进背包，能够满足不超出重量限制，总价值尽可能高。
  *
  * 背包问题又很多变种。这里仅讨论0-1背包问题。
  *
  * 0-1背包：每样物品必须被选取或留下。同一件物品不存在选取一半，也不存在选取很多次的情况。
  */
object Knapsack {

  // 假设：

  // 物品的重量定义为W
  type W = Int
  // 物品的价值定义为V
  type V = Int

  // 对于任意i件物品，存在W1 + W2 + ... + Wi <= W，使得 V1 + V2 + ... + Vi 最大
  // 显然，这是一个NP困难问题。

  // 定义物品
  case class Item(weight: W, value: V)
  type I = Item

  // 物品的数量为n，背包重量为w，则有

  /**
    * 根据背包容纳质量和物品，是否选取
    */
  def maxV(items: List[I], w: W): List[I] = {
    items match {
      case Nil                         => Nil
      case xs :: left if xs.weight > w => maxV(left, w)
      case xs :: left =>
        if (maxV(left, w).map(_.value).sum >= (xs :: maxV(left, w - xs.weight)).map(_.value).sum) maxV(left, w)
        else xs :: maxV(left, w - xs.weight)
    }
  }

  case class Bag(bagged: List[I], maxWeight: W) {
    def isNotFull: Boolean = totWeight <= maxWeight
    def totValue: V        = bagged.map(_.value).sum
    def totWeight: W       = bagged.map(_.weight).sum
  }

  /**
    * 尾递归实现
    */
  @tailrec
  def packer(items: List[I], notPacked: List[Bag], packed: List[Bag], w: W): List[Bag] = {
    def fill(bag: Bag): List[Bag]           = items.map(i => Bag(i +: bag.bagged, w))
    def stuffer(bags: List[Bag]): List[Bag] = bags.map(b => Bag(b.bagged.sortBy(_.weight), w)).distinct
    if (notPacked.isEmpty) packed.sortBy(-_.totValue).take(w)
    else packer(items, stuffer(notPacked.flatMap(fill)).filter(_.isNotFull), notPacked ++ packed, w)
  }
  def max(b1: Bag, b2: Bag): Bag = if (b1.totValue > b2.totValue) b1 else b2
  def maxP(items: List[I], w: W): List[I] = {
    packer(items, items.map(i => Bag(List(i), w)), Nil, w)
      .map(f => Bag(f.bagged.distinct, f.maxWeight))
      .reduceLeft(max)
      .bagged
  }

  // 排序的
  lazy val items = List(Item(1, 1), Item(3, 8), Item(5, 18), Item(6, 22), Item(7, 28))

  def main(args: Array[String]): Unit = {
    val k = maxV(items, 11)
    println("knapsack weight limit:      " + 11 + "(kg)")
    println("items list:                 " + items.map(v => "[%2d, %2d]".format(v.weight, v.value)).mkString(" "))
    println("the items took:             " + k.map(v => "[%2d, %2d]".format(v.weight, v.value)).mkString(" "))
    println("the max value of knapsack:  " + k.map(_.value).sum + "($)")
    println("the max weight of knapsack: " + k.map(_.weight).sum + "(kg)")
    val p = maxP(items, 11)
    println("the items took:             " + p.map(v => "[%2d, %2d]".format(v.weight, v.value)).mkString(" "))
  }
}
