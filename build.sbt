val projectVersion = "0.1.0"

lazy val root = (project in file("."))
  .settings(
    name := "optimal-patterns-scala-http",
    organization := "com.optimalpatterns",
    version := projectVersion,
    scalaVersion := "2.13.9"
  )
  .aggregate(
    `akka-http`,
    `http4s`,
    `zio-http`,
    `http4s-zio2`,
    `finch`,
    `finatra-http`,
    `vertx-web`,
    `zio-tapir`
  )

val LogbackVersion = "1.4.1"
val AkkaVersion = "2.6.20"
val AkkaHttpVersion = "10.2.10"

lazy val `akka-http` = (project in file("akka-http"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "akka-http",
    organization := "com.optimalpatterns",
    version := projectVersion,
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime
    ),
    Docker / packageName := "akka-http-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:11.0.16_8-jre-focal",
    dockerExposedPorts ++= Seq(8080)
  )

val Http4sVersion = "0.23.16"
val CirceVersion = "0.14.3"

lazy val `http4s` = (project in file("http4s"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "http4s",
    organization := "com.optimalpatterns",
    version := projectVersion,
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime
    ),
    Docker / packageName := "http4s-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:11.0.16_8-jre-focal",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `zio-http` = (project in file("zio-http"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "zio-http",
    organization := "com.optimalpatterns",
    version := projectVersion,
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "io.d11" %% "zhttp" % "2.0.0-RC11"
    ),
    Docker / packageName := "zio-http-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:11.0.16_8-jre-focal",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `http4s-zio2` = (project in file("http4s-zio2"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "http4s-zio2",
    organization := "com.optimalpatterns",
    version := projectVersion,
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime,
      "dev.zio" %% "zio" % "2.0.2",
      "dev.zio" %% "zio-interop-cats" % "3.3.0"
    ),
    Docker / packageName := "http4s-zio2-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:11.0.16_8-jre-focal",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `finch` = (project in file("finch"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "finch",
    organization := "com.optimalpatterns",
    version := projectVersion,
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "com.github.finagle" %% "finch-core" % "0.34.0",
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime
    ),
    Docker / packageName := "finch-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:11.0.16_8-jre-focal",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `finatra-http` = (project in file("finatra-http"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "finatra-http",
    organization := "com.optimalpatterns",
    version := projectVersion,
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "com.twitter" %% "finatra-http-server" % "22.7.0",
      "ch.qos.logback" % "logback-classic" % LogbackVersion % Runtime
    ),
    Docker / packageName := "finatra-http-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:11.0.16_8-jre-focal",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val `vertx-web` = (project in file("vertx-web"))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    name := "vertx-web",
    organization := "com.optimalpatterns",
    version := projectVersion,
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "io.vertx" % "vertx-web" % "4.3.3",
      "io.netty" % "netty-all" % "4.1.82.Final"
    ),
    Docker / packageName := "vertx-web-benchmark",
    Docker / version := projectVersion,
    dockerBaseImage := "eclipse-temurin:11.0.16_8-jre-focal",
    dockerExposedPorts ++= Seq(8080)
  )

val tapirVersion = "1.1.1"

lazy val `zio-tapir` = (project in file("zio-tapir")).settings(
  Seq(
    name := "zio-tapir",
    version := "0.1.0-SNAPSHOT",
    organization := "com.optimalpatterns",
    scalaVersion := "2.13.9",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-json-zio" % tapirVersion,
      "ch.qos.logback" % "logback-classic" % "1.4.1",
      "com.softwaremill.sttp.tapir" %% "tapir-sttp-stub-server" % tapirVersion % Test,
      "dev.zio" %% "zio-test" % "2.0.0" % Test,
      "dev.zio" %% "zio-test-sbt" % "2.0.0" % Test,
      "com.softwaremill.sttp.client3" %% "zio-json" % "3.8.0" % Test
    ),
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )
)
