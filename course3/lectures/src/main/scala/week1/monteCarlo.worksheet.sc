import scala.util.Random

def mCount(iter: Int): Int =
  val randomX = Random
  val randomY = Random
  var hits = 0
  for i <- 0 until iter do
    val x = randomX.nextDouble
    val y = randomY.nextDouble
    if x * x + y * y < 1 then hits += 1
  hits

def monteCarloPiSeq(iter: Int): Double = 4.0 * mCount(iter) / iter

monteCarloPiSeq(10000000)
