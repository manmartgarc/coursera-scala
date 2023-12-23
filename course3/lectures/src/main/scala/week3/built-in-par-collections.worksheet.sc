import scala.collection.GenSeq
import scala.collection.parallel.CollectionConverters._

def largestPalindrome(xs: GenSeq[Int]): Int =
  xs.aggregate(Int.MinValue)(
    (largest, x) =>
      if x > largest && x.toString == x.toString.reverse then x else largest,
    math.max
  )

val array = (0 until 1000000).toArray[Int]
largestPalindrome(array)