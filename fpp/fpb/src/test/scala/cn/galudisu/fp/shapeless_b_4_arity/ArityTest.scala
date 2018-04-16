package cn.galudisu.fp.shapeless_b_4_arity

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

class ArityTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

  import shapeless._
  import syntax.std.function._
  import ops.function._

  info("Starting...")

  def applyProduct[P <: Product, F, L <: HList, R](p: P)(f: F)(implicit gen: Generic.Aux[P, L], fp: FnToProduct.Aux[F, L => R]) =
    f.toProduct(gen.to(p))

  test("facilities for abstracting over arity") {
    applyProduct(1, 2)((_: Int) + (_: Int)) should be(3)
    applyProduct(1, 2, 3)((_: Int) * (_: Int) * (_: Int)) should be(6)
  }
}
