def sortList(xs: List[Int]): List[Int] =
  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case Nil     => List(x)
    case y :: ys => if x <= y then x :: xs else y :: insert(x, ys)
  }
  xs match {
    case Nil     => Nil
    case y :: ys => insert(y, sortList(ys))
  }

val myList = List(3, 2, 1)
val strList = List("c", "b", "a")
sortList(myList)

def sortAny[A](xs: List[A])(lessThan: (A, A) => Boolean): List[A] =
  def insert(x: A, xs: List[A]): List[A] = xs match {
    case Nil     => List(x)
    case y :: ys => if lessThan(x, y) then x :: xs else y :: insert(x, ys)
  }
  xs match {
    case Nil     => Nil
    case y :: ys => insert(y, sortAny(ys)(lessThan))
  }

sortAny(myList)((x, y) => x <= y)
sortAny(strList)((x, y) => x.compareTo(y) <= 0)

def sortOrdering[A](xs: List[A])(ord: Ordering[A]): List[A] =
  def insert(x: A, xs: List[A]): List[A] = xs match {
    case Nil     => List(x)
    case y :: ys => if ord.lt(x, y) then x :: xs else y :: insert(x, ys)
  }
  xs match {
    case Nil     => Nil
    case y :: ys => insert(y, sortOrdering(ys)(ord))
  }

sortOrdering(myList)(Ordering.Int)
sortOrdering(strList)(Ordering.String)

def sortImplicit[A](xs: List[A])(using ord: Ordering[A]): List[A] =
  def insert(x: A, xs: List[A]): List[A] = xs match {
    case Nil     => List(x)
    case y :: ys => if ord.lt(x, y) then x :: xs else y :: insert(x, ys)
  }
  xs match {
    case Nil     => Nil
    case y :: ys => insert(y, sortImplicit(ys))
  }

sortImplicit(myList)
sortImplicit(strList)

// Type inference is this
// sort(intList) -> sort[Int](intList)
// ----------------------
// Term inference is this
// sort[Int](intList) -> sort[Int](intList)(using Ordering.Int)
