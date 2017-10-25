package cn.galudisu.fp_8_1_monads

import java.math.MathContext

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

import scala.annotation.tailrec
import scala.language.higherKinds

class MonadTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

  info("Starting... ")

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
    *
    * A monad is just a monoid in the category of endo-functors
    */
  test("sequencing computations") {

    trait Monad[F[_]] {
      def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]
      def unit[A](a: A): F[A]
    }

    val ListMonad = new Monad[List] {
      override def flatMap[A, B](value: List[A])(func: A => List[B]): List[B] = {
        def fun: (List[A]) => List[B] = {
          case Nil => Nil
          case x :: xs => func(x) ::: fun(xs)
        }

        fun(value)
      }
      override def unit[A](a: A) = List(a)
    }

    logger info s"${ListMonad.flatMap(ls)(x => x + 3 :: Nil) }"
  }

  /**
    * Monads simplify
    */
  test("evolution") {

    trait Monad[F[_]] {
      def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]
      def unit[A](a: A): F[A]
    }

    val listMonad = new Monad[List] {
      override def flatMap[A, B](value: List[A])(func: A => List[B]): List[B] = {
        @tailrec
        def fun(a: List[A], b: List[B]): List[B] = a match {
          case Nil => b
          case x :: xs => fun(xs, b ::: func(x))
        }

        fun(value, Nil)
      }
      override def unit[A](a: A) = List(a)
    }

    logger info s"${listMonad.flatMap(ls)(x => x + 3 :: Nil) }"
  }

  test("join") {

    trait Monad[F[_]] {
      def flatMap[A, B](v: F[A])(f: A => F[B]): F[B]
      def unit[A](a: A): F[A]

      def compose[A, B, C](f: A => F[B], g: B => F[C]): A => F[C] = a => flatMap(f(a))(g)
      def join[A, B](mma: F[F[A]]): F[A] = flatMap(mma)(ma => ma)
      def map[A,B](ma: F[A])(f: A => B): F[B] = flatMap(ma)(a => unit(f(a)))
    }

  }
}


































