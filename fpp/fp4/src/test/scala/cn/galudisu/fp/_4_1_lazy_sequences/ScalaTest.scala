package cn.galudisu.fp._4_1_lazy_sequences

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

/**
  * @author galudisu
  */
class ScalaTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

  info("Starting...")

  /*------------
  |   变量部分
  ------------*/
  private var shrink: Double     = _
  private var ls    : List[Int]  = _
  private var as    : Array[Int] = _
  private var group : List[Int]  = _

  before {

    shrink = 0.8
    ls = List(1, 2, 3, 4, 5)
    as = Array(1, 2, 3, 4, 5)
    group = List(1, 2, 3, 4, 5, 9, 11, 20, 21, 22)
  }

  /**
    * lazy val声明的变量，仅当其调用时，才执行内部代码
    */
  test("lazy val") {
    lazy val p = {
      println("Initializing")
      9
    }

    println(p) // 首次将得到输出信息

    println(p)
  }

  /**
    * 无穷序列 Stream
    */
  test("infinite sequences - scala streams") {

    // Scala 包含严格序列(strict sequence)和非严格序列(non-strict sequence)
    // 区分为严格序列要求必须给出所有元素

    1 :: Nil
    1 :: 2 :: Nil

    // 下面三种写法是等价的，方法 `::`表示构造操作
    val p = 4 :: 5 :: 6 :: Nil
    val q = 4 :: (5 :: (6 :: Nil))
    val r = Nil.::(6).::(5).::(4)

    logger info s"$p"
    logger info s"$q"
    logger info s"$r"

    // 方法`:+`表示追加操作

    val list = List(1, 2, 3)
    logger info s"${list :+ 4 }"

    // `::` 的复杂度为O(1)，因为该操作的动作是head::tail，head已经固定
    // `:+` 的复杂度为O(n)，因为要遍历list，得到最后一个元素后，再追加新的元素，因此追加元素是一个耗时的操作，因为每次追加都要遍历一次

    var k = 2
    def f = {
      k += 1
      k
    }

    lazy val s = Stream.continually(f)

    (s take 4) foreach {
      x => println(x)
    }


  }

  /**
    * 递归实现
    */
  test("Recursive streams") {
    lazy val r = Stream.cons(1, Stream.cons(2, Stream.empty))
    logger info s"$r"

    // 不难看出，输出结果为Stream(1,?)，其中`?`表示为tail，`1`表示为head
    (r take 4) foreach { x => println(x) }

    // 其中第二个`con`可以使用递归化形式
    def s(n: Int): Stream[Int] = Stream.cons(n, s(n + 1))
    lazy val q = s(0)
    logger info s"$q"

    // 下面是更简洁的实现
    def succ(n: Int): Stream[Int] = n #:: succ(n + 1)
    lazy val p = succ(0)
    logger info s"$p"
  }

  /**
    * Stream avoid to re-computation, operation react every time
    */
  test("Stream cached result") {
    var k = 3
    def f = {
      println("previous value = " + k)
      k += 1
      k
    }

    val p = Stream.continually(f)

    // Stream inner method will be execute every time
    (p take 3) foreach println


  }

  /**
    *
    */
  test("call by name") {

    // 比较下面两种写法，对比结果
    var k = 0
    def getVal = {
      k += 1
      k
    }

    // Cal-By-Value，表示将计算得到的结果传入参数
    def g(i: Int) = {
      println(s"$i")
      println(s"$i")
      println(s"$i")
    }

    // Cal-By-Name，表示参数的值在每次被访问时执行
    def f(i: => Int) = {
      println(s"$i")
      println(s"$i")
      println(s"$i")
    }

    g(getVal)

    f(getVal)

  }

  /**
    * Stream 作为集合，拥有集合的所有方法和特性
    */
  test("Stream are collection") {

    def succ(n: Int): Stream[Int] = n #:: succ(n + 1)
    lazy val r = succ(0)
    println(r.take(10).mkString(" + "))

    val evenNums = r filter { x => x % 2 == 0 }
    logger info s"$evenNums"
  }

  /**
    * Sieve of Eratosthenes 算法
    */
  test("Sieve of Eratosthenes") {

    def numStream(n: Int): Stream[Int] = Stream.from(n)

    def sieve(stream: Stream[Int]): Stream[Int] = stream.head #:: sieve(stream.tail filter (x => x % stream.head != 0))

    val p = sieve(numStream(2))
    logger info s"$p"

    (p take 5) foreach {println(_)}

    // 由上我们可以得到：
    // 1.
  }

}







































