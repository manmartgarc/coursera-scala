import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

// first group by
val ages = List(2, 52, 44, 23, 17, 14, 12, 82, 51, 64)
val grouped = ages.groupBy(age =>
  if age >= 18 && age < 65 then "adult"
  else if age < 18 then "child"
  else "senior"
)
grouped

// pretty much groupby will break up a collection into two or more collections
// according to a discriminator function. The result is a map where the keys
// are the values returned by the discriminator function and the values are
// collections of elements that returned that value when passed to the
// discriminator function.

case class Event(organizer: String, name: String, budget: Int)

val events = Seq(
  Event("John", "Scala Conference", 1000),
  Event("Mary", "Java Conference", 2000),
  Event("John", "Big Data Conference", 3000),
  Event("Mary", "Ruby Conference", 4000),
  Event("John", "Python Conference", 5000),
  Event("Mary", "Agile Conference", 6000)
)

val spark =
  SparkSession.builder().appName("GroupBy").master("local[*]").getOrCreate()
val sc = spark.sparkContext
val eventsRdd =
  sc.parallelize(events).map(event => (event.organizer, event.budget))
val budgetsRdd = eventsRdd.reduceByKey(_ + _)
