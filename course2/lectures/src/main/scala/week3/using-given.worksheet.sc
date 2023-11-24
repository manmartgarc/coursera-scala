given Int: Ordering[Int] with
  def compare(x: Int, y: Int): Int =
    if x < y then -1 else if x > y then 1 else 0

val intList = List(3, 2, 1)
print(intList.sorted(using Ordering.Int))

summon[Ordering[Int]]
