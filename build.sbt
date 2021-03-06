lazy val akkaHttpVersion = "10.1.5"
lazy val akkaVersion = "2.5.18"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.ruffy",
      scalaVersion := "2.12.8"
    )),
    name := "Possum Server",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  
      "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
      "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
      "org.scalatest" %% "scalatest" % "3.0.5" % Test,

      "ch.qos.logback" % "logback-classic" % "1.2.3",
      
      "com.softwaremill.macwire" %% "macros" % "2.3.1" % "provided",
      "com.softwaremill.macwire" %% "macrosakka" % "2.3.1" % "provided",
      "com.softwaremill.macwire" %% "util" % "2.3.1",
      "com.softwaremill.macwire" %% "proxy" % "2.3.1"
    )
  )
