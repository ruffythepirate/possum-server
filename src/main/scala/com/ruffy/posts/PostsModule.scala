package com.ruffy.posts

import akka.actor.ActorSystem
import com.softwaremill.macwire

trait PostsModule {

  implicit def system: ActorSystem

  private val routes = macwire.wire[PostsRoutes]

  val postRoutes = routes.routes
}
