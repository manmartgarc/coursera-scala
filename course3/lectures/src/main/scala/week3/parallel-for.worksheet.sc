import collection.parallel.CollectionConverters._

// notice that this is deprecated now and had to be imported from scala-parallel-collections
// videos are from 2014-2015...
def initializeArray(xs: Array[Int])(v: Int): Unit = {
  for (i <- (0 until xs.length).par) xs(i) = v
}

val xs = Array(1, 2, 3, 4, 5)
initializeArray(xs)(0)
xs.mkString("[", ", ", "]")
