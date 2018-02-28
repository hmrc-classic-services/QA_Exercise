import org.openqa.selenium.By
import org.scalatest.{FeatureSpec, GivenWhenThen}
import org.scalatest.prop.TableDrivenPropertyChecks._

class ScalaFeatureSpecWithTable extends FeatureSpec with TestSetup with GivenWhenThen with TestProperties{

  /**
    * TODO: Implement the table being read from a CSV
    */
  val events = Table (
    ("Event Name",                "Event Date",       "Event Type",       "Event Description")
    ,("First day of the year",    "2018-01-01 00:00", "Calendar Event",   "New Years Day")
    ,("Last day of the year",     "2018-12-31 23:59", "Calendar Event",   "New Years Eve")
    ,("Tax Returns Due",          "2018-01-31 12:00", "Tax Event",        "Too late now")
  )

  feature("Adding events") {
    scenario("adding an event") {
      forAll (events) { (eventName : String, eventDate : String, eventType : String, eventDescription : String) =>

        Given("A user is on the events page")
        go to host
        click on linkText("Create Event")

        When(s"the user fills out the details for the '$eventName'")
        textField("eventName").value = eventName
        textField("eventDate").value = eventDate
        textField("eventType").value = eventType
        textField("eventDesc").value = eventDescription
        submit()

        Then("the user is taken to the home page with the event details visible")
        currentUrl.split("\\?")(0) shouldBe host + "8080808080"
        val table = webDriver.findElement(By.id("eventList")).getText
        table contains eventName shouldBe true
        table contains transformed(eventDate) shouldBe true
        table contains eventType shouldBe true
        table contains eventDescription shouldBe true
      }
    }

    def transformed(str: String) = { str.replace(" ", "T")  }
  }
}
