import java.sql.Date

import play.api._
import play.api.db.slick._
import play.api.Play.current
import models._

object Global extends GlobalSettings {

  override def onStart(app: Application) = {
    TestData.insert()
  }

  object TestData {
    def insert() = {
      DB.withTransaction { implicit session: Session =>
        if (LunchOptions.count == 0) {
          Seq(
            Provider(1, "Chinese"),
            Provider(2, "Boots")
          ).foreach(Providers.create)
          Seq(
            LunchOption(1, "test1", 1),
            LunchOption(2, "test2", 2),
            LunchOption(3, "test3", 1)
          ).foreach(LunchOptions.create)
          Seq(
            User(1,"TEST")
          ).foreach(Users.create)
          Seq(
            Order(1, 1, 1, new Date(0))
          ).foreach(Orders.create)
        }
      }
    }
  }
}