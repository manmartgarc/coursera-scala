def from(n: Int): LazyList[Int] = n #:: from(n + 1)

val nats = from(0)
nats.take(10)
nats.take(10).toList