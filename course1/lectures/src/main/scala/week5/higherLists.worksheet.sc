def scaleList(xs: List[Double], factor: Double): List[Double] = xs match
  case Nil     => xs
  case y :: ys => y * factor :: scaleList(ys, factor)

def scaleListMap(xs: List[Double], factor: Double): List[Double] =
  xs.map(x => x * factor)

val nums = List(1.0, 2.0, 3.0, 4.0)
scaleList(nums, 0.5)
scaleListMap(nums, 0.5)
nums.map(x => x * 0.5)

def posElems(xs: List[Int]): List[Int] = xs match
  case Nil     => xs
  case y :: ys => if y > 0 then y :: posElems(ys) else posElems(ys)

val numsInt = List(1, -2, 3, -4, 5)
posElems(numsInt)
numsInt.filter(x => x > 0)

// difference between partition and span
val nums2 = List(1, 2, 3, 4, 5, 6)
nums2.partition(x => x % 2 == 0)
nums2.span(x => x % 2 != 0)

def pack[T](xs: List[T]): List[List[T]] = xs match
  case Nil => Nil
  case x :: xs1 =>
    val (more, rest) = xs1.span(y => y == x)
    (x :: more) :: pack(rest)
val elems = List("a", "a", "a", "b", "c", "c", "a")
pack(elems)

def encode[T](xs: List[T]): List[(T, Int)] =
  pack(xs).map(x => (x.head, x.length))
encode(elems)