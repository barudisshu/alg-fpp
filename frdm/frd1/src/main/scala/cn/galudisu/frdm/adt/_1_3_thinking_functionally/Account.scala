package cn.galudisu.frdm.adt._1_3_thinking_functionally

import java.util.Date

case class Balance(amount: BigDecimal = 0)

class Account(no: String, name: String, dateOfOpening: Date, balance: Balance = Balance()) {

  def debit(a: BigDecimal): Account = {
    if (balance.amount < a)
      throw new Exception("Insufficient balance in account")
    new Account(no, name, dateOfOpening, Balance(balance.amount - a))
  }

  def credit(a: BigDecimal) = new Account(no, name, dateOfOpening, Balance(balance.amount + a))
}
