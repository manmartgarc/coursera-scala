package week3

import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}
import org.apache.spark.RangePartitioner

object Partitioning {
  @main def partition: Unit =
    Logger.getLogger("org.apache.spark").setLevel(Level.OFF)
    System.setProperty("hadoop.home.dir", "C:/Users/manma");

    @transient lazy val spark = SparkSession
      .builder()
      .appName("Shuffling")
      .config("spark.master", "local")
      .getOrCreate()

    @transient lazy val sc = spark.sparkContext
    val rdd = sc.parallelize(purchases)
    val purchasesPerCust = rdd.map(p => (p.customerId, p.price)).groupByKey()
    // the data is partitioned by hash partitioning, which is the default
    // partitioning strategy. If the keys are ordered, and the keys have an
    // order, the we should use range partitioning.
    val pairs = rdd.map(p => (p.customerId, p.price))
    val tunedPartitioner = new RangePartitioner(8, pairs)
    // need to persist to avoid reshuffling and repartitioning
    val partitioned = pairs.partitionBy(tunedPartitioner).persist()
    val purchases2 = partitioned.map(p => (p._1, (1, p._2)))
    // a whole lot faster after partitioning!
    val purchasesPerMonth = purchases2
      .reduceByKey((v1, v2) => (v1._1 + v2._1, v1._2 + v2._2))
      .collect()
      .foreach(println)

    spark.stop()
}
