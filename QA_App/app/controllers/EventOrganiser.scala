package controllers

import scala.collection.mutable.HashMap
import java.util.UUID
import java.time.LocalDateTime
import model.Event

object EventOrganiser {
  val events : HashMap[Long, Event] = HashMap()
//TODO cache all events in session memory?
  def nextId : Long = {
    var random = UUID.randomUUID().getMostSignificantBits()
    while(events.keySet.contains(random) || random < 0 ) {
      random = UUID.randomUUID().getMostSignificantBits()
    }
    random
  }

  def addEvent(eventName : String, description : String, eventType : String, dateTime : LocalDateTime) = {
      val id = nextId
      events.put(id, new Event(id, eventName, description, eventType, dateTime))
  }

  def deleteEvent(eventId : Long): Boolean = {
    if(events.contains(eventId)) {
      events.remove(eventId)
      true
    } else {
      false
    }
  }
  def getEvents() = {  events  }

  def clear() = { events.clear() }
}