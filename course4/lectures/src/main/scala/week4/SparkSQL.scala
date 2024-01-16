package week4

import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}

object SparkSQL {

  case class CFFPurchase(customerId: Int, destination: String, price: Double)

  val purchases = Seq(
    CFFPurchase(100, "Geneva", 22.25),
    CFFPurchase(300, "Zurich", 42.10),
    CFFPurchase(100, "Fribourg", 12.40),
    CFFPurchase(200, "St. Gallen", 8.20),
    CFFPurchase(100, "Lucerne", 31.60),
    CFFPurchase(300, "Basel", 16.20)
  )
  @main def shuffle: Unit =
    Logger.getLogger("org.apache.spark").setLevel(Level.OFF)
    System.setProperty("hadoop.home.dir", "C:/Users/manma");

    val spark: SparkSession =
      SparkSession
        .builder()
        .appName("Time Usage")
        .master("local")
        .getOrCreate()

    // For implicit conversions like converting RDDs to DataFrames
    import spark.implicits.{StringToColumn, localSeqToDatasetHolder}
    import scala3encoders.given

    @transient lazy val sc = spark.sparkContext
    val rdd = sc.parallelize(purchases)
    import spark.implicits._
    val df = rdd.toDF()
    df.createOrReplaceTempView("purchases")
    val sqlDF = spark.sql(
      "SELECT customerId, sum(price) FROM purchases GROUP BY customerId ORDER BY customerId"
    )
    sqlDF.show()
    spark.stop()
}
