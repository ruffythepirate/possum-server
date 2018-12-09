package com.ruffy.posts

import akka.actor.ActorSystem
import com.softwaremill.macwire

trait PostsModule {

  implicit def actorSystem: ActorSystem

  val postRoutes = macwire.wire[PostsRoutes]
}
