def removeAt[T](n: Int, xs: List[T]): List[T] = xs match
  case Nil     => Nil
  case y :: ys => if n == 0 then ys else y :: removeAt(n - 1, ys)

val xs = List('a', 'b', 'c', 'd')
removeAt(2, xs)
removeAt(1, xs)

def flatten(xs: Any): List[Any] = xs match
  case Nil     => Nil
  case y :: ys => flatten(y) ++ flatten(ys)
  case _       => xs :: Nil

val ys = List(List(1, 1), 2, List(3, List(5, 8)))
flatten(ys)
