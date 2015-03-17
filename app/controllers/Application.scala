package controllers

import models.LunchOptions
import play.api.db.slick._
import play.api.libs.json._
import play.api.mvc._

object Application extends Controller {

  def index = DBAction { implicit session =>
    Ok(views.html.index(LunchOptions.all()))
  }
}