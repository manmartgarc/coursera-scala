import week2.Rational

// We can extend the class Rational with new methods, without having to create a whole new class.
// Caveats:
// - We can only add new members, not override existing ones.
// - Extensions cannot refer to other class members via this
extension (r: Rational)
  def min(s: Rational): Rational = if s.less(r) then s else r
  def abs: Rational = Rational(r.numer.abs, r.denom)

val r = Rational(1, 2)
val s = Rational(3, 4)
r.min(s)
s.min(r)
r.abs

// We can define operators to use regular infix notation. These should probably be defined
// in the class itself, but we can also define them as extensions.
extension (x: Rational)
    def + (y: Rational): Rational = r.add(y)
    def * (y: Rational): Rational = r.mul(y)
    def - (y: Rational): Rational = r.sub(y)
    def / (y: Rational): Rational = r.mul(Rational(y.denom, y.numer))
    infix def less(y: Rational): Boolean = r.less(y)

val x = Rational(1, 2)
val y = Rational(2, 3)
x + y
x - y
x * y
x / y
x less y