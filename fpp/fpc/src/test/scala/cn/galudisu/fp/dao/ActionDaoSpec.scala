package cn.galudisu.fp.dao

import cn.galudisu.fp.models.Tables.profile.api._
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}

class ActionDaoSpec extends FlatSpec with Matchers with ScalaFutures {
  implicit val defaultPatience: PatienceConfig =
    PatienceConfig(timeout = Span(2, Seconds), interval = Span(5, Millis))

  private val db = Database.forConfig("db")
  private val actionDao = ActionDaoImpl(db)

  "save" should "save the value without exception" in {
    actionDao.save("Hello!").futureValue
  }

  "findById" should "find a row" in {
    val actionId = actionDao.save("New Hello!").futureValue
    val maybeAction = actionDao.findById(actionId).futureValue

    maybeAction.isDefined should be(true)
    maybeAction.get.name should be("New Hello!")
  }

  "findByName" should "find a row" in {
    actionDao.save("galudisu is handsome").futureValue
    val maybeAction = actionDao.filter("galudisu").futureValue

    maybeAction.get.name should be ("galudisu is handsome")
  }
}