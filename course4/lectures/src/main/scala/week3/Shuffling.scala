package week3

import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}

case class CFFPurchase(customerId: Int, destination: String, price: Double)

val purchases = Seq(
  CFFPurchase(100, "Geneva", 22.25),
  CFFPurchase(300, "Zurich", 42.10),
  CFFPurchase(100, "Fribourg", 12.40),
  CFFPurchase(200, "St. Gallen", 8.20),
  CFFPurchase(100, "Lucerne", 31.60),
  CFFPurchase(300, "Basel", 16.20)
)

object Shuffling {
  @main def shuffle: Unit =
    Logger.getLogger("org.apache.spark").setLevel(Level.OFF)
    System.setProperty("hadoop.home.dir", "C:/Users/manma");

    @transient lazy val spark = SparkSession
      .builder()
      .appName("Shuffling")
      .config("spark.master", "local")
      .getOrCreate()

    @transient lazy val sc = spark.sparkContext

    val rdd = sc.parallelize(purchases)
    val purchasePerMonth = rdd
      .map(p => (p.customerId, p.price))
      // a shuffle happens here because the keys might be on different
      // partitions, therefore on different nodes.
      .groupByKey()
      .map(p => (p._1, (p._2.size, p._2.sum)))
      .collect()
      .foreach(println)

    val purchasesPerMonth2 = rdd
      .map(p => (p.customerId, (1, p.price)))
      // this uses less shuffling because the reduce is applied on the same
      // key before the shuffle.
      // reducing before shuffling is called map-side reduction.
      .reduceByKey((v1, v2) => (v1._1 + v2._1, v1._2 + v2._2))
      .collect()
      .foreach(println)

    spark.stop()
}
