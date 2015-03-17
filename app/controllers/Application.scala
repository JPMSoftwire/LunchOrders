package controllers

import play.api._
import play.api.mvc._
import models.LunchOption

object Application extends Controller {

  def index = Action {
    val options: List[LunchOption] = List(new LunchOption, new LunchOption)
    Ok(views.html.index(options))
  }

}