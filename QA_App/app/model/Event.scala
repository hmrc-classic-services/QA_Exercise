package model

import java.time.LocalDateTime
import utils.DateUtil

case class Event(eventId : Long, eventName : String, description : String, eventType : String, dateTime : LocalDateTime) {
  def entry: String = {
    val sb = new StringBuilder
    sb.append("[").append(eventId).append("]").append(eventName).append(" : ").append(DateUtil.dateAsString(dateTime))
    sb.toString
  }

  def isAfter(event: Event): Boolean = event.dateTime.isAfter(this.dateTime)

  def isBefore(event: Event): Boolean = event.dateTime.isBefore(this.dateTime)
}