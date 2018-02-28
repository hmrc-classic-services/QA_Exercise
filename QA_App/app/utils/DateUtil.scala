package utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


object DateUtil {
  val fullPattern = "yyyy-MM-dd HH:mm"
  def dateAsString(dateTime: LocalDateTime): String = {
    dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
  }

  def dateAndTimeAsString(dateTime: LocalDateTime): String = {
    dateTime.format(DateTimeFormatter.ofPattern(fullPattern))
  }

  def parse(string : String): LocalDateTime = {
      LocalDateTime.parse(string, DateTimeFormatter.ofPattern(fullPattern))
  }
}