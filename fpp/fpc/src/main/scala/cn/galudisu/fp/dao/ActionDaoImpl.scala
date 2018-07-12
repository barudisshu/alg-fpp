package cn.galudisu.fp.dao

import java.util.concurrent.Executors

import cn.galudisu.fp.models.Tables._
import cn.galudisu.fp.models.Tables.profile.api._
import cn.galudisu.fp.models._
import slick.lifted.Query

import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}

case class ActionDaoImpl(db: Database) extends ActionDao {
  private implicit val ec: ExecutionContextExecutorService = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(5))

  override def findById(id: Long): Future[Option[Tables.ActionRow]] = {
    val query: Query[Tables.Action, Tables.Action#TableElementType, Seq] = Action.filter(_.id === id)
    val action = query.result.headOption
    db.run(action)
  }

  override def save(name: String): Future[Long] = {
    val query = Action.map(a => a.name)
    val actions = (for {
      actionInsert <- query += name
      actionId <- sql"SELECT LAST_INSERT_ID()".as[(Long)].head
    } yield actionId).transactionally
    db.run(actions)
  }

  override def filter(name: String): Future[Option[Tables.ActionRow]] = {
    val action = Action.filter(_.name like s"%$name%").result.headOption
    db.run(action)
  }
}