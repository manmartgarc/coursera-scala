def from(n: Int): LazyList[Int] = n #:: from(n + 1)

val nats = from(0)
nats.take(10)
nats.take(10).toList

/** The Sieve of Eratosthenes to find prime numbers iteratively.
  *
  * @param s
  *   the integers to evaluate
  * @return
  *   the filtered list with only prime numbers
  */
def sieve(s: LazyList[Int]): LazyList[Int] =
  s.head #:: sieve(s.tail.filter(_ % s.head != 0))

val primes = sieve(from(2))
primes.take(10).toList

def sqrtSeq(x: Double): LazyList[Double] =
  def improve(guess: Double) = (guess + x / guess) / 2
  lazy val guesses: LazyList[Double] = 1 #:: guesses.map(improve)
  guesses

sqrtSeq(2).take(10).toList

def isGoodEnough(guess: Double, x: Double) =
  math.abs((guess * guess - x) / x) < 1E-10

sqrtSeq(4).filter(isGoodEnough(_, 4)).head