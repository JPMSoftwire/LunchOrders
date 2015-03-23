package models

import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.Json

import scala.slick.lifted.Tag

case class User(id:Long, initials: String)

object User {
  implicit val userFormat = Json.format[User]
}

class Users(tag: Tag) extends Table[User](tag, "User") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def initials = column[String]("initials")

  def * = (id, initials) <>((User.apply _).tupled, User.unapply)
}

object Users {
  val users = TableQuery[Users]

  def create(user: User)(implicit s: Session): Long = {
    (users returning users.map(_.id)) += user
  }

  def find(id: Long)(implicit s: Session) = {
    users.filter(_.id === id).first
  }

  def all()(implicit s: Session) = {
    users.list
  }
}
