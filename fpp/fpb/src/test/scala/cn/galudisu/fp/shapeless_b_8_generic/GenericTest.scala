package cn.galudisu.fp.shapeless_b_8_generic

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}
import shapeless._
import syntax.std._
import syntax.singleton._

class GenericTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {

  info("Starting...")

  case class Foo(i: Int, s: String, b: Boolean)
  val fooGen = Generic[Foo]
  val foo    = Foo(23, "foo", true)

  test("hlist representation") {

    val l = fooGen.to(foo)
    l should be(23 :: "foo" :: true :: HNil)

    val r = 13 :: l.tail
    val newFoo = fooGen.from(r)

    newFoo.i should be(13)
  }

  import poly._

  // Simple recursive case class family
  sealed trait Tree[T]
  case class Leaf[T](t: T) extends Tree[T]
  case class Node[T](left: Tree[T], right: Tree[T]) extends Tree[T]

  // Polymorphic function which adds 1 to any Int and is the identity
  // on all other values
  object inc extends -> ((i: Int) => i + 1)

  val tree: Tree[Int] =
    Node(
      Leaf(1),
      Node(
        Leaf(2),
        Leaf(3)
      )
    )

  // Transform tree by applying inc everywhere
  test("transform") {
    everywhere(inc)(tree) should be(
      Node(Leaf(2),
        Node(Leaf(3), Leaf(4))
      )
    )
  }


  test("symbolic") {
    import record._
    case class Book(author: String, title: String, id: Int, price: Double)
    val bookGen = LabelledGeneric[Book]
    val tapl = Book("Benjamin Pierce", "Types and Programming Languages", 262162091, 44.11)
    val rec = bookGen.to(tapl)
    rec('price) should be(44.11)

    val updatedBook = bookGen.from(rec.updateWith('price)(_ + 2.0))

    updatedBook.price should be(46.11)

    import syntax.singleton._

    case class ExtendedBook(author: String, title: String, id: Int, price: Double, inPrint: Boolean)
    val bookExtGen = LabelledGeneric[ExtendedBook]

    val extendedBook = bookExtGen.from(rec + ('inPrint ->> true))

    extendedBook.inPrint should be(true)
  }

}
