package com.ruffy.posts

import akka.actor.{ Actor, ActorLogging, Props }

final case class Post(id: String, title: String, text: String)
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
