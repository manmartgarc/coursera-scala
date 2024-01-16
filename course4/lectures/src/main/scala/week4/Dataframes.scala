package week4

import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.functions._

object Dataframes {
  case class Employee(
      id: Int,
      fname: String,
      lname: String,
      age: Int,
      city: String
  )

  val employees = Seq(
    Employee(1, "John", "Doe", 32, "New York"),
    Employee(2, "Mary", "Smith", 25, "Chicago"),
    Employee(3, "Kevin", "Johnson", 52, "Chicago"),
    Employee(4, "Jane", "Doe", 32, "New York"),
    Employee(5, "Joe", "Smith", 25, "Chicago"),
    Employee(6, "Kevin", "Johnson", 52, "Chicago"),
    Employee(7, "Jane", "Doe", 32, "New York"),
    Employee(8, "John", "Smith", 25, "Chicago"),
    Employee(9, "Kevin", "Johnson", 52, "Chicago"),
    Employee(10, "Jane", "Doe", 32, "New York")
  )

  @main def dfs: Unit =
    Logger.getLogger("org.apache.spark").setLevel(Level.OFF)
    System.setProperty("hadoop.home.dir", "C:/Users/manma");

    val spark: SparkSession =
      SparkSession
        .builder()
        .appName("dfs")
        .master("local[*]")
        .getOrCreate()

    // For implicit conversions like converting RDDs to DataFrames
    import spark.implicits.{StringToColumn, localSeqToDatasetHolder}
    import scala3encoders.given

    @transient lazy val sc = spark.sparkContext
    val rdd = sc.parallelize(employees)
    import spark.implicits._
    val df = rdd.toDF()
    df.printSchema()
    // three ways to select columns
    // 1. with implicits
    df.filter($"id" === 10).show()
    // 2. referring to dataframe
    df.filter(df("id") === 10).show()
    // 3. with SQL
    df.filter("id = 10").show()

    df.select("id", "lname").where("city = 'Chicago'").orderBy("id").show()
    df.filter(($"city" === "Chicago") && ($"age" > 25)).show()
    df.groupBy($"city").mean("age").show()
    df.groupBy($"city").agg(mean("age"), max("age")).show()
    spark.stop()
}
