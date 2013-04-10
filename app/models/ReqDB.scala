package models

import play.api.db._
import anorm._
import play.api.Play.current
import anorm.SqlParser._
import play.api.libs.json._

case class ReqDB(data: String) {

  def addData {
    DB.withConnection { implicit c =>
      val id: Int = SQL("insert into req (data) values ({data})").
        on('data -> this.data).executeUpdate()
    }
  }
}

object ReqDB {

  val data = {
    get[String]("data") map {
      case data => ReqDB(data)
    }
  }

  def getAll: List[ReqDB] = {
    return dbAccess(SQL("Select * from req"))
  }
  
  def getRecent(current: Int, limit:Int): List[ReqDB] = {
    return dbAccess(SQL("SELECT * FROM req WHERE id > {current} ORDER BY id DESC LIMIT {limit}").on('current -> current, 'limit -> limit))
  }
  
  def dbAccess(sql:Sql): List[ReqDB] = {
    DB.withConnection { implicit c =>
      val datas = sql.as(ReqDB.data *)
      return datas
    }
  }
}


