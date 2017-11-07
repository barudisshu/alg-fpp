package cn.galudisu.frdm.adt

import com.typesafe.scalalogging.StrictLogging
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

class ScalaTest extends FunSuite with Matchers with BeforeAndAfter with StrictLogging {

  info("starting... ")

  var xs: List[Int] = _

  before {
    xs = List(1, 3, 5, 7, 9, 11)
  }

  test("purlfying model") {

    import java.util.{Date, Calendar}
    import scala.util.{Try, Success, Failure}

    def today = Calendar.getInstance.getTime

    type Amount = BigDecimal

    case class Balance(amount: Amount = 0)

    // Account aggregate is now an ADT
    case class Account(no: String, name: String,
                       dateOfOpening: Date, balance: Balance = Balance())

    trait AccountService {

      def debit(a: Account, amount: Amount): Try[Account] = {
        if (a.balance.amount < amount)
          Failure(new Exception("Insufficient balance in account"))
        else
          Success(a.copy(balance = Balance(a.balance.amount - amount)))
      }

      def credit(a: Account, amount: Amount): Try[Account] =
        Success(a.copy(balance = Balance(a.balance.amount + amount)))
    }

    object AccountService extends AccountService

    val a = Account("a1", "John", today)

    assert(a.balance == Balance())

    val b = AccountService.credit(a, 1000)

    b shouldBe Success(a.copy(balance = Balance(1000)))

  }

}




































