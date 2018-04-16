package cn.galudisu.fp.shapeless_b_9_lenses

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}
import shapeless._
import syntax._

class LensesTest extends FunSuite with BeforeAndAfter with Matchers with LazyLogging {


  // A pair of ordinary case classes ...
  case class Address(street: String, city: String, postcode: String)
  case class Person(name: String, age: Int, address: Address)

  // Some lenses over Person/Address ...
  val nameLens = lens[Person] >> 'name
  val ageLens = lens[Person] >> 'age
  val addressLens = lens[Person] >> 'address
  val streetLens = lens[Person] >> 'address >> 'street
  val cityLens = lens[Person] >> 'address >> 'city
  val postcodeLens = lens[Person] >> 'address >> 'postcode

  val person = Person("Joe Grey", 37, Address("Southover Street", "Brighton", "BN2 9UA"))


  test("lenses") {
    ageLens.get(person) should be(37)

    val updatedPerson = ageLens.set(person)(38)
    updatedPerson.age should be(38)

    val updatedPerson1 = ageLens.modify(person)(_ + 1)
    updatedPerson1.age should be(38)

    streetLens.get(person) should be("Southover Street")

    val updatedPerson3 = streetLens.set(person)("Montpelier Road")
    updatedPerson3.address.street should be("Montpelier Road")
  }
}
