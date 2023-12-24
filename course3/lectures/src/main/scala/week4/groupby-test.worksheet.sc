def mean(xs: Iterable[Int]): Double =
  xs.sum.toDouble / xs.size

val x = (0 until 100000).toList
x.groupBy(_ % 1000).view.mapValues(mean(_)).toMap
