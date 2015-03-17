package models

import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.Json

import scala.slick.lifted.Tag

case class LunchOption(id: Long, choice: String, providerId: Long)

object LunchOption {
  implicit val lunchOptionFormat = Json.format[LunchOption]
}

class LunchOptions(tag: Tag) extends Table[LunchOption](tag, "LunchOption") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def choice = column[String]("choice")
  def providerId = column[Long]("lunchProviderId")

  def * =(id, choice, providerId) <> ((LunchOption.apply _).tupled, LunchOption.unapply)
}

object LunchOptions {
  val lunchOptions = TableQuery[LunchOptions]

  def all()(implicit s: Session) = {
    lunchOptions.list
  }

  def count(implicit s: Session): Int = {
    Query(lunchOptions.length).first
  }

  def create(lunchOption: LunchOption)(implicit s: Session) = {
    (lunchOptions returning lunchOptions.map(_.id)) += lunchOption
  }

  def find(id: Long)(implicit s: Session) = {
    lunchOptions.filter(_.id === id).first
  }

  def byProvider(providerId: Long)(implicit s: Session) = {
    lunchOptions.filter(_.providerId === providerId).list
  }
}