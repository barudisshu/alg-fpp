package cn.galudisu

import scala.language._
import scala.language.experimental.macros

/**
  * 题龙阳县青草湖
  *
  * 作者：唐温如
  *
  * 西风吹老洞庭波，一夜湘君白发多。
  * 醉后不知天在水，满船清梦压星河。
  */
package object typecheck {
  def unexpected : Nothing = sys.error("Unexpected invocation")

  // 类型编程转换规则
  // • ADT Values: val        -> object
  // • Members: def x / val x -> type X
  // • def f(x)               -> type F[X]
  // • a.b                    -> A#B
  // • x: T                   -> X <: T
  // • new A(b)               -> A[B]

  // 类
  type CC[T] = {
    type F[P[_]] = P[T]
  }

  type FT

  // Like a.b
  type ι[P[_]] = P[({type C = FT})#C]

  type Id[+A] = A
  type Functor[A] = A => A
}
