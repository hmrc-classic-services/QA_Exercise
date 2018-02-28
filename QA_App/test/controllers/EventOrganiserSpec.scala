package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import org.scalatest._
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import util.Random

import scala.util.Random


class EventOrganiserSpec extends FeatureSpec with BeforeAndAfter with Matchers with GivenWhenThen {

  val fullPattern = "yyyy-MM-dd HH:mm"
  def parseDate(string : String) = LocalDateTime.parse(string, DateTimeFormatter.ofPattern(fullPattern))

  feature("The Event Organiser") {
    scenario("should start empty") {
      EventOrganiser.getEvents.size shouldBe 0
    }

    before {
      EventOrganiser.clear
    }

    after {
      EventOrganiser.clear
    }

    def addTestEvent =  EventOrganiser.addEvent(
      "test name",
      "test description",
      "test event",
      parseDate("2017-12-31 13:40"))

    scenario("should allow us to add an event") {
      EventOrganiser.getEvents.size shouldBe 0
      addTestEvent
      EventOrganiser.getEvents.size shouldBe 1
    }

    scenario("should allow us to add a second event") {
      addTestEvent
      addTestEvent
      EventOrganiser.getEvents.size shouldBe 2
    }

    scenario("should allow us to remove an event") {
      addTestEvent
      EventOrganiser.getEvents.size shouldBe 1
      val ids = EventOrganiser.getEvents.keySet.toVector
      EventOrganiser.deleteEvent(ids(0))
      EventOrganiser.getEvents.size shouldBe 0
    }

    scenario("events should be retained after mass addition and deletions") {
      val random = new Random()
      val randomCount = random.nextInt(100)
      for (count <- 1 to randomCount) {
        addTestEvent
      }
      EventOrganiser.getEvents.size shouldBe randomCount

      val randomDelete = random.nextInt(randomCount)
      val ids = EventOrganiser.getEvents.keySet.toVector
      for (count <- 1 to randomDelete) {
        EventOrganiser.deleteEvent(ids(count))
      }
      EventOrganiser.getEvents.size shouldBe (randomCount - randomDelete)

    }
  }
}
