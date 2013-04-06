package models

import play.api.db._
import anorm._
import play.api.Play.current
import anorm.SqlParser._
 
case class ReqDB(data: String) {
 
    def addData {
        DB.withConnection { implicit c =>
            val id: Int = SQL("insert into req (data) values ({data})").
                on('data->this.data).executeUpdate()
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
        DB.withConnection { implicit c =>
            val datas = SQL("Select * from req").as(ReqDB.data *)
            return datas
        }
    }
}