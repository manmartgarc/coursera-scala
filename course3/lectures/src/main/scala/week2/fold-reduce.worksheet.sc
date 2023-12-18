List(1, 3, 8).foldLeft(100)((s, x) => s - x)
List(1, 3, 8).foldRight(100)((s, x) => s - x)
List(1, 3, 8).reduceLeft((s, x) => s - x)
List(1, 3, 8).reduceRight((s, x) => s - x)

sealed abstract class Tree[A]
case class Leaf[A](value: A) extends Tree[A]
case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]

def reduce[A](t: Tree[A], f: (A, A) => A): A = t match {
  case Leaf(v)    => v
  case Node(l, r) => f(reduce(l, f), reduce(r, f))
}

val tree = Node(Leaf(1), Node(Leaf(3), Leaf(8)))
def fMinus = (x: Int, y: Int) => x - y
reduce[Int](tree, fMinus)
