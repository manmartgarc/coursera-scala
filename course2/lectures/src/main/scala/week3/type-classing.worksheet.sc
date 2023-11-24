case class Rational(num: Int, den: Int)

trait Ordering[A]:
  def compare(x: A, y: A): Int

given RationalOrdering: Ordering[Rational] with
  def compare(x: Rational, y: Rational): Int =
    val xn = x.num * y.den
    val yn = y.num * x.den
    if xn < yn then -1 else if xn > yn then 1 else 0


