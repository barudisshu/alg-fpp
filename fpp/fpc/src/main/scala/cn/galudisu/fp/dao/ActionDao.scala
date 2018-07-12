package cn.galudisu.fp.dao

import cn.galudisu.fp.models._

import scala.concurrent.Future

/**
 * Dao for action table
 */
trait ActionDao {
  def findById(id: Long): Future[Option[Tables.ActionRow]]
  def save(name: String): Future[Long]
  def filter(name: String): Future[Option[Tables.ActionRow]]
}