import scala.annotation.tailrec

def power(x: Double, exp: Int): Double =
  var r = 1.0
  var i = exp
  while i > 0 do { r = r * x; i = i - 1 }
  r

power(2, 12)

@tailrec
final def whileDo(condition: => Boolean)(command: => Unit): Unit =
  if condition then
    command
    whileDo(condition)(command)
  else ()

@tailrec
final def repeatUntil(command: => Unit)(condition: => Boolean): Unit =
  command
  if !condition then repeatUntil(command)(condition)
  else ()

var x = 0
var y = 2
repeatUntil {
  x = x + 1
  y = y * 2
}(x == 5)
y

for i <- 1 until 3; j <- "abc" do println(s"$i $j")
(1 until 3).foreach(i => "abc".foreach(j => println(s"$i $j")))
