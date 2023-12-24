val scala3Version = "3.3.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "course4",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    scalacOptions ++= Seq("-language:implicitConversions", "-deprecation"),
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "0.7.29" % Test,
      ("org.apache.spark" %% "spark-core" % "3.2.0").cross(CrossVersion.for3Use2_13),
      ("org.apache.spark" %% "spark-sql" % "3.2.0").cross(CrossVersion.for3Use2_13),
    ),
    run / fork := true,
    Test / fork := true
  )
