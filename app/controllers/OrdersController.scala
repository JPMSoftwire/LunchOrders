package controllers

import models.{Order, Orders}
import play.api.db.slick._
import play.api.libs.json.Json
import play.api.mvc.Controller


object OrdersController extends Controller {
  def create = DBAction(parse.json) { implicit requestSession =>
    val newOrder = requestSession.body.as[Order]
    Ok(Json.toJson(Orders.find(Orders.create(newOrder))))
  }

  def all = DBAction { implicit session =>
    Ok(Json.toJson(Orders.all()))
  }
}