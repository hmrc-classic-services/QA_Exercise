import org.scalatest.{FlatSpec, Matchers}
import language.postfixOps

class ScalaFlatSpec extends FlatSpec with TestSetup with TestProperties {
      "The delete event page" should "have a textbox for the event id" in {
          go to host
          pageTitle shouldBe "Welcome to the Event Planner"
          click on linkText("Delete Event")
          checkPageTitle("Event Planner - Delete Event")
      }
}
