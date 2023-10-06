import scala.annotation.tailrec

def sumTail(f: Int => Int, a: Int, b: Int): Int = {
  @tailrec
  def loop(a: Int, acc: Int): Int =
    if a > b then acc else loop(a + 1, f(a) + acc)
  loop(a, 0)
}

// Looking at the definitions below, we are repeatedly passing in the parameters (a, b)
// Can we get rid of this repetition?
def sumInts(a: Int, b: Int): Int = sumTail(x => x, a, b)
def sumCubes(a: Int, b: Int): Int = sumTail(x => x * x * x, a, b)
sumInts(1, 5)

// Yes, we can use currying. Let's redefine sum as a function that returns a function.
/** This is a tail recursive function that returns a function that returns the
  * sum f(x) for all integers x between a and b.
  *
  * Notice the tail recursive function sumF that is returned.
  *
  * The sum function is curried. It takes a function f as a parameter, and returns
  * a function sumF that takes two parameters a and b, and returns the sum of f(x)
  *
  * @param f
  * @return
  */
def sum(f: Int => Int): (Int, Int) => Int =
  def sumF(a: Int, b: Int): Int =
    @tailrec
    def loop(a: Int, acc: Int): Int =
      if a > b then acc else loop(a + 1, f(a) + acc)
    loop(a, 0)
  sumF

sum(x => x)(1, 5)

// We can now define sumInts again
def sumIntsCurr = sum(x => x)
sumIntsCurr(1, 5)
