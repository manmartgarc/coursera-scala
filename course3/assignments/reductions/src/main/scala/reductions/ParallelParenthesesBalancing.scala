package reductions

import scala.annotation.*
import org.scalameter.*

object ParallelParenthesesBalancingRunner:

  @volatile var seqResult = false

  @volatile var parResult = false

  val standardConfig = config(
    Key.exec.minWarmupRuns := 40,
    Key.exec.maxWarmupRuns := 80,
    Key.exec.benchRuns := 120,
    Key.verbose := false
  ) withWarmer (Warmer.Default())

  def main(args: Array[String]): Unit =
    val length = 100000000
    val chars = new Array[Char](length)
    val threshold = 10000
    val seqtime = standardConfig measure {
      seqResult = ParallelParenthesesBalancing.balance(chars)
    }
    println(s"sequential result = $seqResult")
    println(s"sequential balancing time: $seqtime")

    val fjtime = standardConfig measure {
      parResult = ParallelParenthesesBalancing.parBalance(chars, threshold)
    }
    println(s"parallel result = $parResult")
    println(s"parallel balancing time: $fjtime")
    println(s"speedup: ${seqtime.value / fjtime.value}")

object ParallelParenthesesBalancing
    extends ParallelParenthesesBalancingInterface:

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
    */
  def balance(chars: Array[Char]): Boolean =

    def loop(chars: Array[Char], idx: Int, acc: Int): Boolean =
      if (acc < 0) false
      else if (idx == chars.length) acc == 0
      else if (chars(idx) == '(') loop(chars, idx + 1, acc + 1)
      else if (chars(idx) == ')') loop(chars, idx + 1, acc - 1)
      else loop(chars, idx + 1, acc)

    loop(chars, 0, 0)

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
    */
  def parBalance(chars: Array[Char], threshold: Int): Boolean =

    def traverse(idx: Int, until: Int, arg1: Int, arg2: Int) = {
      for (i <- idx until until) yield {
        if (chars(i) == '(') (arg1 + 1, arg2)
        else if (chars(i) == ')') (arg1, arg2 + 1)
        else (arg1, arg2)
      }
    }

    def reduce(from: Int, until: Int) = {
      val (arg1, arg2) = traverse(from, until, 0, 0).foldLeft((0, 0)) {
        case ((a1, a2), (b1, b2)) => (a1 + b1, a2 + b2)
      }
      arg1 == arg2
    }

    reduce(0, chars.length) == true

  // For those who want more:
  // Prove that your reduction operator is associative!
