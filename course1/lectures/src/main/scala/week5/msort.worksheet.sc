def msort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] =
  val n = xs.length / 2
  if n == 0 then xs
  else
    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match
      case (Nil, ys) => ys
      case (xs, Nil) => xs
      case (x :: xs1, y :: ys1) =>
        if lt(x, y) then x :: merge(xs1, ys)
        else y :: merge(xs, ys1)

    val (fst, snd) = xs.splitAt(n)
    merge(msort(fst)(lt), msort(snd)(lt))

val xs = List(-5, 6, 3, 2, 7)
val fruits = List("apple", "pear", "orange", "pineapple")

msort(xs)((x, y) => x < y)
msort(fruits)((x, y) => x.compareTo(y) < 0)
