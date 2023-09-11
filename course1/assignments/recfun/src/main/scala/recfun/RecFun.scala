package recfun

import scala.annotation.tailrec

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do print(s"${pascal(col, row)} ")
      println()

  /** Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /** Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    @tailrec
    def loop(chars: List[Char], openCount: Int): Boolean = {
      if (chars.isEmpty) {
        openCount == 0
      } else if (chars.head == '(') {
        loop(chars.tail, openCount + 1)
      } else if (chars.head == ')') {
        openCount > 0 && loop(chars.tail, openCount - 1)
      } else {
        loop(chars.tail, openCount)
      }
    }
    loop(chars, 0)
  }

  /** Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (money < 0 || coins.isEmpty) 0
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
