val r: Range = 1 until 5
val s: Range = 1 to 5
val t: Range = 1 to 20 by 2
r.foreach(x => print(s"$x "))
s.foreach(x => print(s"$x "))
t.foreach(x => print(s"$x "))

val nums = Vector(1, 2, 3, -88)
nums :+ 4
4 +: nums

nums.exists(x => x == -88)
nums.forall(x => x > 0)
val zipped = nums.zip(Vector("a", "b", "c", "d"))
zipped.unzip

def listNumbers(n: Int, m: Int) =
  (1 to m).flatMap(x => (1 to n).map(y => (x, y)))

listNumbers(3, 9).length

def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
  if (xs.length != ys.length)
    throw new IllegalArgumentException("Vectors must have same length")
//   else xs.zip(ys).map((x, y) => x * y).sum
  else xs.zip(ys).map(_ * _).sum

scalarProduct(Vector(1, 2, 3), Vector(4, 5, 6))

def isPrime(n: Int): Boolean =
  (2 until n).forall(n % _ != 0)

isPrime(7)
isPrime(4)
isPrime(1231247)
