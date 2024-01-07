import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext
case class Taco(kind: String, price: Double)

val tacoOrder = List(
  Taco("Carnitas", 2.25),
  Taco("Corn", 1.75),
  Taco("Barbacoa", 2.50),
  Taco("Chicken", 2.00),
  Taco("Chicken", 6.5)
)

// couple of ways to sum

// this is two passes over the list
tacoOrder.map(t => t.price).reduce(_ + _)

// this is one pass over the list
tacoOrder.foldLeft(0.0)((total, taco) => total + taco.price)

// aggregate is a combination of fold and foldLeft (but its deprecated)
// aggregate is also parallelizable and allows to change the return type
tacoOrder.aggregate(0.0)((total, taco) => total + taco.price, _ + _)
// above is equivalent to
tacoOrder.foldLeft(0.0)((total, taco) => total + taco.price)

// since foldLeft is not parallelizable, it's not available on spark

val sc = SparkSession
  .builder()
  .appName("Tacos")
  .master("local")
  .getOrCreate()
  .sparkContext
val pairRdd = sc.parallelize(tacoOrder).map(t => (t.kind, t.price))
