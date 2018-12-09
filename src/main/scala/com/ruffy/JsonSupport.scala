package com.ruffy

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import com.ruffy.UserRegistryActor.ActionPerformed
import com.ruffy.posts.{ Post, Posts }
import spray.json.{ JsString, JsValue, JsonFormat }

//#json-support
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport {
  // import the default encoders for primitive types (Int, String, Lists etc)
  import DefaultJsonProtocol._

  import spray.json.deserializationError

  implicit object LocalDateTimeFormat extends JsonFormat[LocalDateTime] {
    def write(dateTime: LocalDateTime) = JsString(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
    def read(value: JsValue) = value match {
      case JsString(dateTime) => LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
      case _ => deserializationError(s"LocalDateTime expected, got $value")
    }
  }

  implicit val userJsonFormat = jsonFormat3(User)
  implicit val usersJsonFormat = jsonFormat1(Users)

  implicit val postJsonFormat = jsonFormat7(Post)
  implicit val postsJsonFormat = jsonFormat1(Posts)

  implicit val actionPerformedJsonFormat = jsonFormat1(ActionPerformed)
}
//#json-support
