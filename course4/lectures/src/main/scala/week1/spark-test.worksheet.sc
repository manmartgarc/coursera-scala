import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

val sc = SparkSession.builder().master("local[0]").getOrCreate().sparkContext

val rdd: RDD[Int] = sc.parallelize(1 to 1000000)
rdd.min()
rdd.max()
