def isPrime(x: Int): Boolean =
  (2 until x) forall (x % _ != 0)

// bad because it's not lazy
(1000 to 10000).filter(isPrime)(1)

val vs = LazyList.cons(1, LazyList.cons(2, LazyList.empty))
vs.head

// good because lazy
(1000 to 10000).to(LazyList).filter(isPrime)(1)

def lazyRange(lo: Int, hi: Int): LazyList[Int] =
  if lo >= hi then LazyList.empty
  else LazyList.cons(lo, lazyRange(lo + 1, hi))

lazyRange(1000, 10000).filter(isPrime)(15)

LazyList.range(1000, 10000).filter(isPrime)(15)
