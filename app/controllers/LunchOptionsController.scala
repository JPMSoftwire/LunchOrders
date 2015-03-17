package controllers

import models.{LunchOption, LunchOptions}
import play.api.db.slick._
import play.api.libs.json.Json
import play.api.mvc.Controller

object LunchOptionsController extends Controller {
  def all = DBAction { implicit session =>
    Ok(Json.toJson(LunchOptions.all()))
  }

  def create = DBAction(parse.json) { implicit requestSession =>
    val newLunchOption = requestSession.body.as[LunchOption]
    Ok(Json.toJson(LunchOptions.find(LunchOptions.create(newLunchOption))))
  }

  def lunchOptionByProvider(provider: Long) = DBAction { implicit session =>
    Ok(Json.toJson(LunchOptions.byProvider(provider)))
  }
}