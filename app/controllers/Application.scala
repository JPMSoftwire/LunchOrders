package controllers

import play.api._
import play.api.db.slick._
import play.api.libs.json._
import play.api.mvc._
import models.{LunchOptions, LunchOption}

object Application extends Controller {

  def index = DBAction { implicit session =>
    Ok(views.html.index(LunchOptions.all()))
  }

  def all = DBAction { implicit session =>
    Ok(Json.toJson(LunchOptions.all()))
  }
}