package example

@main def run(): Unit = {
  println(s"Sum of 1, 4, 2 = ${Lists.sum(List(1, 4, 2))}")
  println(s"Max of 1, 9, 2 = ${Lists.max(List(1, 9, 2))}")
}
