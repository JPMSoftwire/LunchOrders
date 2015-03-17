package models

import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.Json

import scala.slick.lifted.Tag

case class Provider(id: Long, choice: String)

object Provider {
  implicit val lunchOptionFormat = Json.format[Provider]
}

class Providers(tag: Tag) extends Table[Provider](tag, "Providers") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * =(id, name) <> ((Provider.apply _).tupled, Provider.unapply)
}

object Providers {
  val providers = TableQuery[Providers]

  def all()(implicit s: Session) = {
    providers.list
  }

  def count(implicit s: Session): Int = {
    Query(providers.length).first
  }

  def create(provider: Provider)(implicit s: Session) = {
    (providers returning providers.map(_.id)) += provider
  }

  def find(id: Long)(implicit s: Session) = {
    providers.filter(_.id === id).first
  }
}