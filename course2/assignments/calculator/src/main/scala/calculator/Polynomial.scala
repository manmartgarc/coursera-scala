package calculator

object Polynomial extends PolynomialInterface:
  def computeDelta(
      a: Signal[Double],
      b: Signal[Double],
      c: Signal[Double]
  ): Signal[Double] =
    Signal {
      val bVal = b()
      bVal * bVal - 4 * a() * c()
    }

  def computeSolutions(
      a: Signal[Double],
      b: Signal[Double],
      c: Signal[Double],
      delta: Signal[Double]
  ): Signal[Set[Double]] =
    Signal {
      val deltaVal = delta()
      if (deltaVal < 0) Set()
      else {
        val aVal = a()
        val bVal = b()
        val sqrtDelta = math.sqrt(deltaVal)
        Set(
          (-bVal + sqrtDelta) / (2 * aVal),
          (-bVal - sqrtDelta) / (2 * aVal)
        )
      }
    }
