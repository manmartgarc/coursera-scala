package quickcheck

import org.scalacheck.*
import Arbitrary.*
import Gen.*
import Prop.forAll

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap:
  lazy val genHeap: Gen[H] = for
    item <- arbitrary[Int]
    heap <- oneOf(const(empty), genHeap)
  yield insert(item, heap)

  given Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if isEmpty(h) then 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("min1") = forAll { (a: Int) =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("minPair") = forAll { (a: Int, b: Int) =>
    val h = insert(b, insert(a, empty))
    val minValue = Math.min(a, b)
    findMin(h) == minValue
  }

  property("deleteMin") = forAll { (a: Int) =>
    val h = deleteMin(insert(a, empty))
    isEmpty(h) == true
  }

  property("exhaustingHeapIsSorted") =
    def check(h: H): Boolean =
      if isEmpty(h) || isEmpty(deleteMin(h)) then true
      else
        val minVal = findMin(h)
        val newHeap = deleteMin(h)
        val newMinVal = findMin(newHeap)
        minVal <= newMinVal && check(newHeap)

    forAll { (h: H) =>
      check(h)
    }

  property("minOfMeldingHeaps") =
    def check(heap1: H, heap2: H, meldedHeap: H): Boolean =
      if isEmpty(meldedHeap) then isEmpty(heap1) && isEmpty(heap2)
      else if isEmpty(heap1) then findMin(meldedHeap) == findMin(heap2)
      else if isEmpty(heap2) then findMin(meldedHeap) == findMin(heap1)
      else if findMin(meldedHeap) == findMin(heap1) then
        check(deleteMin(heap1), heap2, deleteMin(meldedHeap))
      else if findMin(meldedHeap) == findMin(heap2) then
        check(heap1, deleteMin(heap2), deleteMin(meldedHeap))
      else false

    forAll { (heap1: H, heap2: H) =>
      val meldedHeap = meld(heap1, heap2)
      check(heap1, heap2, meldedHeap)
    }
