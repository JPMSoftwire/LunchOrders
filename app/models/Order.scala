package models
import java.sql.Date

import play.api.libs.json.Json
import play.api.db.slick.Config.driver.simple._

import scala.slick.lifted.Tag

case class Order(id: Long, lunchOptionId: Long, userId: Long, date: Date)

object Order {
  implicit val orderFormat = Json.format[Order]
}

class Orders(tag: Tag) extends Table[Order](tag, "Order") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def lunchOptionId = column[Long]("lunchOptionId")
  def userId = column[Long]("userId")
  def date = column[Date]("date")

  def lunchOption = foreignKey("lunchOptionForeignKey", lunchOptionId, LunchOptions.lunchOptions)(_.id)
  def user = foreignKey("userForeignKey", userId, Users.users)(_.id)

  def * = (id, lunchOptionId, userId, date) <>((Order.apply _).tupled, Order.unapply)
}

object Orders {
  val orders = TableQuery[Orders]

  def create(order: Order)(implicit s: Session): Long = {
    (orders returning orders.map(_.id)) += order
  }

  def find(id: Long)(implicit s: Session) = {
    orders.filter(_.id === id).first
  }

  def all()(implicit s: Session) = {
    orders.list
  }
}