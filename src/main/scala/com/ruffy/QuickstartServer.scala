package com.ruffy

//#quick-start-server
import akka.actor.{ ActorRef, ActorSystem }
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.ruffy.posts.PostModule

import scala.concurrent.duration.Duration
import scala.concurrent.{ Await, ExecutionContext, Future }
import scala.util.{ Failure, Success }

//#main-class
object QuickstartServer extends App with UserRoutes with PostModule {

  // set up ActorSystem and other dependencies here
  //#main-class
  //#server-bootstrapping
  implicit val system: ActorSystem = ActorSystem("possum-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher
  //#server-bootstrapping

  val userRegistryActor: ActorRef = system.actorOf(UserRegistryActor.props, "userRegistryActor")

  //#main-class
  // from the UserRoutes trait
  lazy val routes = userRoutes ~ postRoutes
  //#main-class

  //#http-server
  val serverBinding: Future[Http.ServerBinding] = Http().bindAndHandle(routes, "localhost", 8080)

  serverBinding.onComplete {
    case Success(bound) =>
      println(s"Server online at http://${bound.localAddress.getHostString}:${bound.localAddress.getPort}/")
    case Failure(e) =>
      Console.err.println(s"Server could not start!")
      e.printStackTrace()
      system.terminate()
  }

  Await.result(system.whenTerminated, Duration.Inf)
  //#http-server
  //#main-class
}
//#main-class
//#quick-start-server
