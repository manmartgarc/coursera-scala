import scala.collection.parallel.CollectionConverters._

(1 until 100000).par
  .filter(n => n % 3 == 0)
  .count(n => n.toString == n.toString.reverse)

def badSum(xs: Array[Int]): Int =
  // foldLeft is not associative, so the result is not run in parallel
  xs.par.foldLeft(0)(_ + _)

badSum((1 until 10).toArray[Int])