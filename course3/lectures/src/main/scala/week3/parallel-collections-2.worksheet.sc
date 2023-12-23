import scala.collection.parallel.CollectionConverters._

def myMax(xs: Array[Int]): Int =
  xs.par.foldLeft(Int.MinValue)((x, y) => if x > y then x else y)

myMax((1 until 101).toArray[Int])

def play(a: String, b: String): String = List(a, b).sorted match
  case "paper" :: "scissors" :: _ => "scissors"
  case "paper" :: "rock" :: _     => "paper"
  case "rock" :: "scissors" :: _  => "rock"
  case a :: b :: _ if a == b      => a
  case "" :: b :: _               => b
  case a :: "" :: _               => a
  case _                          => "error"

play("paper", "rock")
play("paper", "scissors")

// according to the lecture, this is not deterministic. This is because the
// play operator is commutative but not associative.
Array("paper", "rock", "paper", "scissors").par.fold("")(play)

def isVowel(c: String): Boolean = "aeiou".contains(c.toLowerCase)

// The aggregate method takes two functions. The first function is used to
// combine elements within a partition. The second function is used to combine
// the results of the first function across partitions.

// This is more powerful than foldLeft because it allows us to specify a
// different type for the result of the first function. In this case, we are
// counting the number of vowels in the array, so the result of the first
// function is an Int. The result of the second function is also an Int, so we
// can use the same function for both.
Array("E", "P", "F", "L").par.aggregate(0)(
  (count, c) => if isVowel(c) then count + 1 else count,
  _ + _
)
