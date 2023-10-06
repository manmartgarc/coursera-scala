val romanNumerals: Map[String, Int] = Map("I" -> 1, "V" -> 5, "X" -> 10)
val capitalOfCountry = Map("US" -> "Washington", "Switzerland" -> "Bern")
val countryOfCapital = capitalOfCountry.map((x, y) => (y, x))
capitalOfCountry("US")

// Notice the .get() method returns an Option[T] type
capitalOfCountry.get("US")
capitalOfCountry.get("Andorra")

def showCapital(country: String) = capitalOfCountry.get(country) match
  case Some(capital) => capital
  case None          => "Missing Data"

showCapital("Chile")

capitalOfCountry + ("Chile" -> "Santiago")

val fruits = List("apple", "pear", "orange", "pineapple")
fruits.sortWith(_.length < _.length)
fruits.sorted
fruits.groupBy(_.head)

// x ^ 3 - 2x + 5
Map(0 -> 5, 1 -> -2, 3 -> 1)

// we can define a map as a total function
val cap1 = capitalOfCountry.withDefaultValue("<unknown>")
cap1("Chile")

class Polynom(nonZeroTerms: Map[Int, Double]):

  def this(bindings: (Int, Double)*) = this(bindings.toMap)

  val terms = nonZeroTerms.withDefaultValue(0.0)

  infix def +(other: Polynom): Polynom =
    // Polynom(terms ++ other.terms.map((exp, coeff) => (exp, terms(exp) + coeff)))
    Polynom(other.terms.foldLeft(terms)(addTerm))

  def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] =
    val (exp, coeff) = term
    terms + (exp -> (terms(exp) + coeff))

  override def toString(): String =
    val termStrings =
      for (exp, coeff) <- terms.toList.sorted.reverse
      yield
        val exponent =
          if exp == 0 then "" else if exp == 1 then s"x" else s"x^$exp"
        s"$coeff$exponent"
    if terms.isEmpty then "0"
    else termStrings.mkString(" + ")

val x = Polynom(0 -> 2, 1 -> 3, 2 -> 1)
val z = Polynom(Map())
x + x + z
