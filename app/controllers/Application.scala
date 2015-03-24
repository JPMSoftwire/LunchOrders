package controllers

import models.{Providers, LunchOptions}
import play.api.db.slick._
import play.api.libs.json._
import play.api.mvc._

object Application extends Controller {

  def index = DBAction { implicit session =>
    Ok(views.html.index(Providers.all()))
  }

  def addOrder(provider: Long) = Action {
    Ok(views.html.addOrder(provider))
  }
}