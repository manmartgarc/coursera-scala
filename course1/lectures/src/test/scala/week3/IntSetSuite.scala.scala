package week3.class_hierarchies

class IntSetSuite extends munit.FunSuite:
  test("EmptySet contains nothing") {
    assert(!Empty.contains(1))
  }

  test("EmptySet does not contain 1") {
    assert(!Empty.contains(1))
  }

  test("IntSet(1) contains 1") {
    assert(IntSet(1).contains(1))
  }

  test("IntSet Tuple contains all elements") {
    assert(IntSet(1, 2).contains(1))
    assert(IntSet(1, 2).contains(2))
  }

  test("IntSet varargs contains all elements") {
    val ints = 0 until 100
    val set = IntSet(ints*)
    ints.foreach { i =>
      assert(set.contains(i))
    }
  }

  test("Union of two empty sets is empty") {
    assert(Empty.union(Empty) == Empty)
  }

  test("Union of empty set and non-empty set is non-empty set") {
    val union = IntSet().union(IntSet(1))
    assert(clue(union.contains(1)))
    assert(clue(!union.contains(0)))
  }
