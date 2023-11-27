package example

@main def run(): Unit =
  val xs = List(1, 3, 2)
  println(f"Sum of $xs is ${Lists.sum(xs)}")
  println(f"Max of $xs is ${Lists.max(xs)}")
