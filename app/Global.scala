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
            LunchOption(1, "test1", 20),
            LunchOption(2, "test2", 50),
            LunchOption(3, "test3", 20)
          ).foreach(LunchOptions.create)

        }
      }
    }
  }
}