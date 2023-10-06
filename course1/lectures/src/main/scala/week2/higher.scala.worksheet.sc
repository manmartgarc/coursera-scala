import scala.annotation.tailrec

/** This is a recursive function that sums all integers between a and b.
  *
  * Notice that the function is not tail recursive, because the recursive call
  * is not the last operation performed by the function. Notice also that the
  * function bottoms out when a > b, returning 0, at which point the recursive
  * calls are unwound.
  *
  * @param a
  *   the lower bound
  * @param b
  *   the upper bound
  * @return
  *   the sum of all integers between a and b
  */
def sumInts(a: Int, b: Int): Int =
  if a > b then 0 else a + sumInts(a + 1, b)

sumInts(1, 10)

/** This is a higher order function that takes a function f and applies it to
  * all integers between a and b, summing the results.
  *
  * Notice that the function is not tail recursive, because the recursive call
  * is not the last operation performed by the function. Notice also that the
  * function bottoms out when a > b, returning 0, at which point the recursive
  * calls are unwound.
  *
  * This is a generalization of sumInts, which is a special case of this
  * function, which is aptly named the sum function.
  *
  * @param f
  *   the function to apply to all integers between a and b
  * @param a
  *   the lower bound
  * @param b
  *   the upper bound
  * @return
  *   the sum of all integers between a and b
  */
def sum(f: Int => Int, a: Int, b: Int): Int =
  if a > b then 0 else f(a) + sum(f, a + 1, b)

def id(x: Int): Int = x
def cube(x: Int): Int = x * x * x
def fact(x: Int): Int = if x == 0 then 1 else x * fact(x - 1)

// We can rewrite sumInts, sumCubes, and sumFactorials in terms of sum.
def sumIntsBetter(a: Int, b: Int): Int = sum(id, a, b)
def sumCubesBetter(a: Int, b: Int): Int = sum(cube, a, b)
def sumFactorialsBetter(a: Int, b: Int): Int = sum(fact, a, b)

sumIntsBetter(1, 5)
sumCubesBetter(1, 5)
sumFactorialsBetter(1, 5)
if sumInts(1, 5) == sumIntsBetter(1, 5) then println("OK") else println("KO")

// We can also get away without defining each function separately, and use anonymous functions instead.
def sumIntsLambda(a: Int, b: Int) = sum(x => x, a, b)
sumIntsLambda(1, 5)
if sumIntsBetter(1, 5) == sumIntsLambda(1, 5) then println("OK")
else println("KO")

// This is an amazing trick. We can transform any linearly recursive function into
// a tail recursive function by passing an accumulator as an additional parameter.

/** This is a tail recursive function that sums all integers between a and b.
  *
  * The trick is to pass an accumulator as an additional parameter. The
  * accumulator is initialized to 0, and is updated at each recursive call. The
  * function bottoms out when a > b, returning the accumulator, at which point
  * the recursive calls are unraveled.
  *
  * @param f
  *   the function to apply to all integers between a and b
  * @param a
  *   the lower bound
  * @param b
  *   the upper bound
  * @return
  *   the sum of f(x) for all integers x between a and b
  */
def sumTail(f: Int => Int, a: Int, b: Int): Int = {
  @tailrec
  def loop(a: Int, acc: Int): Int =
    if a > b then acc else loop(a + 1, f(a) + acc)
  loop(a, 0)
}
sumTail(x => x, 1, 5)
