package com.ruffy.posts

import akka.actor.{ ActorRef, ActorSystem }
import akka.event.Logging
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.pattern.ask
import akka.util.Timeout
import com.ruffy.JsonSupport
import com.ruffy.posts.PostRegistryActor._

import scala.concurrent.Future
import scala.concurrent.duration.DurationLong

class PostRoutes(implicit val system: ActorSystem) extends JsonSupport {

  lazy val log = Logging(system, classOf[PostRoutes])

  // the post request actor.
  lazy val postRegistryActor: ActorRef = system.actorOf(PostRegistryActor.props, "postRegistryActor")

  implicit lazy val timeout = Timeout(5.seconds)

  lazy val routes: Route =
    pathPrefix("posts") {
      pathEnd {
        get {
          log.info("Getting posts")
          val posts: Future[Posts] =
            (postRegistryActor ? GetPosts).mapTo[Posts]
          complete(posts)
        }
      }
    }
}
