def isort(xs: List[Int]): List[Int] =
  def insert(x: Int, xs: List[Int]): List[Int] = xs match
    case List()  => List(x)
    case y :: ys => if x < y then x :: xs else y :: insert(x, ys)
  xs match
    case List()  => List()
    case y :: ys => insert(y, isort(ys))

val myList: List[Int] = List(3, 4, 99, -1, 2, 1, 0)
isort(myList)
