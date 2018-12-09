package com.ruffy.posts

import java.time.LocalDateTime

import akka.actor.{ Actor, ActorLogging, Props }

final case class Post(id: String, title: String, tags: Set[String], createdBy: String, updatedBy: String, updated: LocalDateTime, created: LocalDateTime)
final case class Posts(posts: Seq[Post])

object PostRegistryActor {

  final case object GetPosts

  def props = Props[PostRegistryActor]
}

class PostRegistryActor extends Actor with ActorLogging {
  import PostRegistryActor._

  var users = Set.empty[Post]

  def receive: Receive = {
    case GetPosts =>
      sender() ! Posts(users.toSeq)
  }
}
