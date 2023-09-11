package week1

def square(x: Double) = x * x
def abs(x: Double) = if x > 0 then x else -x

/** Newton's method for finding square roots
  *
  * @param x
  *   the number to find the square root of
  * @return
  *   the square root of x
  */
def sqrt(x: Double) = {

  /** Recursive function to find the square root of x
    *
    * @param guess
    *   the current guess
    * @return
    *   the square root of x
    */
  def sqrtIter(guess: Double): Double =
    if isGoodEnough(guess) then guess
    else sqrtIter(improve(guess))

  /** Improves the current guess
    *
    * @param guess
    *   the current guess
    * @return
    *   the improved guess via Newton's method
    */
  def improve(guess: Double) =
    (guess + x / guess) / 2

  /** Checks if the current guess is good enough
    *
    * @param guess
    *   the current guess
    * @param precision
    *   the precision to check against. Defaults to 1e-6
    * @return
    *   true if the guess is good enough, false otherwise
    */
  def isGoodEnough(guess: Double, precision: Double = 1e-6) =
    abs(square(guess) - x) < precision

  sqrtIter(1.0)
}

@main def testSqrt = println(sqrt(2))
