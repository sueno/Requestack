package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json._
import models._

object RequestCtrl extends Controller {

  /**
   * requestform mapping ReqDB class
   */
  val rform = Form[ReqDB](mapping("data" -> text)(ReqDB.apply)(ReqDB.unapply))

  /**
   * insert form
   */
  def datainsert = Action { implicit request =>
    val myForm = rform.bindFromRequest
    val data: ReqDB = myForm.get
    val result = data.addData
    Ok(data.toString())
  }

  /**
   * ReqDB value mapping JsValue 
   */
  implicit object ReqDBWrites extends Writes[ReqDB] {
    def writes(r: ReqDB): JsValue = JsObject(List("data" -> JsString(r.data)))
  }

  val dform = Form(tuple("current" -> number, "limit" -> number))
  /**
   * get data
   */
  def getdata = Action { implicit request =>
    val myForm = dform.bindFromRequest.get
    val reqData = ReqDB.getRecent(myForm._1, myForm._2)
    val json: JsValue = Json.toJson(reqData);
    Ok(json)
  }
}
