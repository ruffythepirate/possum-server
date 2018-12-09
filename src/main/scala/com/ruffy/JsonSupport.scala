package com.ruffy

import com.ruffy.UserRegistryActor.ActionPerformed
import com.ruffy.posts.{ Post, Posts }

//#json-support
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport {
  // import the default encoders for primitive types (Int, String, Lists etc)
  import DefaultJsonProtocol._

  implicit val userJsonFormat = jsonFormat3(User)
  implicit val usersJsonFormat = jsonFormat1(Users)

  implicit val postJsonFormat = jsonFormat3(Post)
  implicit val postsJsonFormat = jsonFormat1(Posts)

  implicit val actionPerformedJsonFormat = jsonFormat1(ActionPerformed)
}
//#json-support
