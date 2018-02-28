import com.gargoylesoftware.htmlunit.{BrowserVersion, WebClient}
import org.openqa.selenium.{By, WebDriver}
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.Matchers
import org.scalatest.concurrent.Eventually
import org.scalatest.selenium.WebBrowser

trait TestSetup extends Matchers with Eventually with WebBrowser {
  //This file is to enable and disable some features of the test setup. These should not need editing
  implicit val webDriver: WebDriver = new HtmlUnitDriver
  implicit val client = new WebClient(BrowserVersion.CHROME)
  client.getOptions.setThrowExceptionOnFailingStatusCode(false)
  client.getOptions.setThrowExceptionOnScriptError(false)
  java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF)

  def checkPageTitle( title : String) = {
    webDriver.findElements(By.id("proposition-name")).size shouldBe 1
    webDriver.findElements(By.id("proposition-name")).get(0).getText shouldBe title
  }

}
