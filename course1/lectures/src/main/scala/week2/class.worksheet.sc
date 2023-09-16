
import week2.Rational
import scala.annotation.tailrec
// Sometimes we need more than the built-in types. For example, we may want to
// do arithmetic on rational numbers. We could represent a rational number as a
// pair of integers: a numerator and a denominator. But there are two problems
// with this representation. First, it allows the representation of invalid
// rationals (with denominator 0). Second, a rational number can be represented
// in many ways (e.g., 1/2 == 2/4 == 3/6). We would like to have a single
// representation for each rational number. The solution is to represent a
// rational number as an object of a class.


// keyword "new" is not needed to create a new object because it is not a
// constructor call, but a call to a factory method named "apply" that every
// class implicitly has.
val x = Rational(1, 2)
println(x.numer)
println(x.denom)

def addRational(r: Rational, s: Rational): Rational =
  Rational(
    r.numer * s.denom + s.numer * r.denom,
    r.denom * s.denom
  )

def makeString(r: Rational): String =
  s"${r.numer}/${r.denom}"

makeString(addRational(Rational(1, 2), Rational(2, 3)))

val r1 = Rational(1, 3)
val r2 = Rational(5, 7)
val r3 = Rational(3, 2)
r1.add(r2).mul(r3)
r1.sub(r2).sub(r3)

val r = Rational(1, 2)
val s = Rational(2, 4)

val rlx1 = Rational(1, 2)
val rlx2 = Rational(2, 3)
rlx1.max(rlx2)
rlx1.less(rlx2)
rlx2.less(rlx1)

val rconst1 = Rational(3)
