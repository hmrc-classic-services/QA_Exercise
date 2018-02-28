import org.openqa.selenium.By
import org.scalatest.{FeatureSpec, GivenWhenThen}

class ScalaFeatureTest extends FeatureSpec with TestSetup with GivenWhenThen with TestProperties{
  /**
    * Anyone unfamiliar with scala specs should look inot the resources here
    * code
    * http://www.scalatest.org/getting_started_with_feature_spec
    */


  feature("The Index Page") {

    scenario("should have a title welcoming them") {
      go to host
      pageTitle should be("Welcome to the Event Planner")
    }

    scenario("should contain the company name") {
      go to host
      webDriver.findElements(By.id("logo")).size shouldBe 1
    }

    scenario("should have a link that takes the user the add Event page") {
      go to host
      webDriver.findElements(By.linkText("Create Event")).size shouldBe 1
      click on linkText("Create Event")
      eventually {
          currentUrl shouldBe createEventPage
          checkPageTitle("Event Planner - Add Event")
      }
    }
  }

  feature("Adding events") {
      scenario("adding an event") {
        Given("A user is on the events page")
        go to host
        click on linkText("Create Event")

        When("the user fills out the details")
        textField("eventName").value = "Testing Event 1"
        textField("eventDate").value = "1992-08-18 22:55"
        textField("eventType").value = "Test Event"
        textField("eventDesc").value = "No 1 Testing Event"
        submit()

        Then("the user is taken to the home page with the event details visible")
        currentUrl.split("\\?")(0) shouldBe host
        val table = webDriver.findElement(By.id("eventList")).getText
        table contains "Testing Event 1" shouldBe true
        table contains "1992-08-18T22:55" shouldBe true
        table contains "Test Event" shouldBe true
        table contains "No 1 Testing Event" shouldBe true
      }
  }

  feature("Adding events via the url") {
    scenario("if I use the URI to add an event"){
      Given("a user is on the main page")
      go to host

      val eventName = "Start"
      val eventDate = "1990-11-08T00:00"
      val eventType = "Social"
      val eventDesc = "Start event 2"

      When("they enter the url to add an event")
      go to "http://localhost:8090/events/add?eventName=Start&eventDate=1990-11-08+00%3A00&eventType=Social&eventDesc=Start+event2"
      Then("the user is taken to the home page with the event details visible")
      val table = webDriver.findElement(By.id("eventList")).getText
        table contains eventName shouldBe true
        table contains eventDate shouldBe true  
        table contains eventType shouldBe true
    }
  }

}
