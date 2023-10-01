def sumRec(xs: List[Int]): Int = xs match
  case Nil     => 0
  case y :: ys => y + sumRec(ys)

val nums = List(1, 2, 3, 4, 5)
sumRec(nums)
nums.sum

def sum(xs: List[Int]): Int = (0 :: xs).reduceLeft((x, y) => x + y)
def sumShort(xs: List[Int]): Int = (0 :: xs).reduceLeft(_ + _)
def product(xs: List[Int]): Int = (1 :: xs).reduceLeft((x, y) => x * y)
def productShort(xs: List[Int]): Int = (1 :: xs).reduceLeft(_ * _)
sum(nums)
sumShort(nums)
product(nums)
productShort(nums)

def sumFold(xs: List[Int]): Int = xs.foldLeft(0)(_ + _)
def productFold(xs: List[Int]): Int = xs.foldLeft(1)(_ * _)
sumFold(nums)
productFold(nums)

def reverse[T](xs: List[T]): List[T] =
  xs.foldLeft(List[T]())((xs, x) => x :: xs)

reverse(nums)

def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  xs.foldRight(List[U]())((y, ys) => f(y) :: ys)

def lengthFun[T](xs: List[T]): Int =
  xs.foldRight(0)((_, ys) => ys + 1)

mapFun(nums, (x: Int) => x * x)
lengthFun(nums)

1 :: List(1, 2, 3)
