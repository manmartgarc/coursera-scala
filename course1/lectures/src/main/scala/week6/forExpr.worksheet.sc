def isPrime(n: Int): Boolean = (2 until n).forall(d => n % d != 0)

// Ugly way
def filterPrimesUgly(n: Int) =
  (1 until n)
    .flatMap(i => (1 until i).map(j => (i, j)))
    .filter((x, y) => isPrime(x + y))

// Useful law
// xs.flatMap(f) == xs.map(f).flatten

isPrime(7)
filterPrimesUgly(7)

case class Person(name: String, age: Int)

val persons = List(
  Person("Alice", 25),
  Person("Bob", 30),
  Person("Charlie", 32)
)
// these two are equivalent
for p <- persons if p.age > 25 yield p.name
persons.filter(x => x.age > 25).map(x => x.name)

// for s yield e
// s is a sequence of generators and filters, and e is an expresion
// whose value is returned

// a generator is of the form p <- e, where p is a pattern and e
// an expression whose value is a collection
// A filter is of the form if f where f is a boolean expression

def filterPrimes(n: Int) =
  for
    i <- 1 until n
    j <- 1 until i
    if isPrime(i + j)
  yield (i, j)

filterPrimes(7)

def scalarProduct(xs: List[Double], ys: List[Double]): Double =
  if xs.length != ys.length then
    throw new IllegalArgumentException("different lengths")
  else
    val prods = for (x, y) <- xs.zip(ys) yield x * y
    prods.sum

scalarProduct(List(1, 2, 3), List(4, 5, 6))