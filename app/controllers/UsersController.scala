package controllers

import models.{User, Users}
import play.api.db.slick._
import play.api.libs.json.Json
import play.api.mvc.Controller


object UsersController extends Controller {
  def create = DBAction(parse.json) { implicit requestSession =>
    val newUser = requestSession.body.as[User]
    Ok(Json.toJson(Users.find(Users.create(newUser))))
  }

  def all = DBAction { implicit session =>
    Ok(Json.toJson(Users.all()))
  }
}