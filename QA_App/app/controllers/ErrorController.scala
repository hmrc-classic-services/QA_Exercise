package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import utils.DateUtil

import scala.util.Random

@Singleton
class ErrorController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def general() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.error())
  }
}
