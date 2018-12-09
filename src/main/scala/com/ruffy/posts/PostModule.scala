package com.ruffy.posts

import akka.actor.ActorSystem
import com.softwaremill.macwire

trait PostModule {

  implicit def system: ActorSystem

  private lazy val routes = macwire.wire[PostRoutes]

  lazy val postRoutes = routes.routes
}
