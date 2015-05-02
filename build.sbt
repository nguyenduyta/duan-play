import play.Project._

name := """nhansu"""

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.2.2",
  "org.webjars" % "bootstrap" % "2.3.1",
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.mindrot" % "jbcrypt" % "0.3m",
  javaEbean,
  javaCore,
  javaJdbc
  )

playJavaSettings