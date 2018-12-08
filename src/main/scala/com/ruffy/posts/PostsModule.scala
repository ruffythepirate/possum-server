package com.ruffy.posts

import com.softwaremill.macwire

class PostsModule {

  val postRoutes = macwire.wire[PostsRoutes]
}
