import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
class SparkSuite extends munit.FunSuite {
  test("Spark works?") {
    val sc = SparkSession.builder
      .master("local")
      .appName("Spark Suite")
      .getOrCreate()
      .sparkContext
    sc.setLogLevel("ERROR")
    val rdd: RDD[Int] = sc.parallelize(1 until 101)
    assertEquals(rdd.sum().toInt, 100 * 101 / 2)
  }
}
