package week2

import scala.annotation.tailrec

class Rational(x: Int, y: Int):
  require(y != 0, "denominator must be positive")
  def this(x: Int) = this(x, 1)
  @tailrec
  private def gcd(a: Int, b: Int): Int =
    if b == 0 then a else gcd(b, a % b)
  val g = gcd(x.abs, y)
  val numer = x / g
  val denom = y / g

  def add(r: Rational) =
    new Rational(
      numer * r.denom + r.numer * denom,
      denom * r.denom
    )

  def mul(r: Rational) =
    Rational(numer * r.numer, denom * r.denom)

  def neg: Rational = Rational(-numer, denom)

  def sub(r: Rational) = add(r.neg)

  def less(that: Rational) =
    this.numer * that.denom < that.numer * this.denom

  def max(that: Rational) =
    if this.less(that) then that else this

  override def toString(): String = s"$numer/$denom"
end Rational
