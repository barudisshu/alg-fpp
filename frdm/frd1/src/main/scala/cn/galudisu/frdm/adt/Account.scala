package cn.galudisu.frdm.adt

import java.util.Date

import scala.util.Try

/**
  * Lifecycle of a domain object
  */
trait Domain {

  type E
  type H
  type P

  // Every object (entity or value object) in any model must have a definite lifecycle pattern
  // thus events will be using repeatedly in various contexts of domain modeling.
  def creation: E
  def behaviors: H
  def persistence: P
}

/**
  * Bank is also an entity,
  * an entity can contain other entities or value objects
  */
trait Bank {

  // attributes of bank
  def debit: Unit   // 贷款
  def issue: Unit   // 转账


}

case class Address(latitude: Long, longitude: Long)


/**
  * In fact, [[Account]] is a aggregate root
  */
trait Account {                     // Basic contract of an Account aggregate
  def no: String
  def name: String
  def bank: Bank                    // Reference to another entity
  def address: Address              // Address is a value object.
  def dateOfOpening: Date
  def dateOfClose: Option[Date]
  // ..
}

// Factory Pattern be used
object Account {
  // define apply method
}



case class CheckingAccount(         // 具体实现
  no: String,
  name: String,
  bank: Bank,
  address: Address,
  dateOfOpening: Date,
  dateOfClose: Option[Date]
  // ..
) extends Account

/*
 * In reality, when you design aggregates, you may find that for performance and consistency of operations you have to
 * optimize away many composing entities from the aggregate and have only the root along with the value objects. For
 * example, you may choose to keep a bank ID instead of the entire Bank entity as part of the Account aggregate.
 */

case class SavingsAccount(
 no: String,
 name: String,
 bank: Bank,
 address: Address,
 dateOfOpening: Date,
 dateOfClose: Option[Date],

  // ..
  rateOfInterest: BigDecimal,
  // ..
) extends Account

// A domain model is a blueprint of the relationships between the various entities of the problem domain and
// sketches out other important details, such as following:
/*
 * Objects that belong to the domain
 * Behaviors that those objects demonstrate in interacting among themselves
 * The language that the domain speaks
 * The context within which the model operates
 */

trait AccountService {
  type Amount = BigDecimal
  def transfer(from: Account, to: Account, amount: Amount): Option[Amount]
}

/**
  * Repository doesn't have any knowledge of the nature of the underlying persistent store.
  */
trait AccountRepository {
  def query(accountNo: String): Option[Account]
  def query(criteria: Criteria[Account]): Seq[Account]
  def write(accounts: Seq[Account]): Boolean
  def delete(account: Account): Boolean
}

case class Criteria[T](keywords: Option[String])

/*
 * 1. supply a bunch of arguments to the factory and get back an aggregate(such as `Account`).
 * 2. use the aggregate as your contract through all behaviors that you implement through services.
 * 3. use the aggregate to persist the entity in the repository
 */

/**
  * [[AccountServiceImpl]] respect the qualities of understandability
  * 1. The function body doesn't contain any irrelevant details. It just encapsulates the domain logic
  * 2. The implementation used terms from the domain of banking, so business domain doesn't know anything about
  * the underlying implementation platform, it is reference transparent.
  * 3. The implementation narrates use method composing and monadic and takes care of any exceptions in the sequence of execution.
  */
trait AccountServiceImpl extends AccountService{

  // These methods call `Ubiquitous language`

  def debit(a: Account, amount: Amount): Try[Account]
  def credit(a: Account, amount: Amount): Try[Account]

  def transfer(from: Account, to: Account, amount: Amount): Try[(Account,Account)] = for {
    d <- debit(from, amount)
    c <- credit(to, amount)
  } yield (d,c)

}



























