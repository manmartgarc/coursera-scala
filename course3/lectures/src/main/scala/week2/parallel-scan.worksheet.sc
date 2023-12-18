List(1, 3, 8).scan(100)((s, x) => s + x)

def scanLeft[A](inp: Array[A], a0: A, f: (A, A) => A, out: Array[A]): Unit =
  out(0) = a0
  var a = a0
  var i = 0
  while (i < inp.length) {
    a = f(a, inp(i))
    i += 1
    out(i) = a
  }

val out = new Array[Int](4)
scanLeft(Array(1, 3, 8), 0, (x: Int, y: Int) => x + y, out)
out.toList