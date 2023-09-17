package funsets

import scala.concurrent.duration.*

/** This class is a test suite for the methods in object FunSets.
  *
  * To run this test suite, start "sbt" then run the "test" command.
  */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /** When writing tests, one would often like to re-use certain values for
    * multiple tests. For instance, we would like to create an Int-set and have
    * multiple test about it.
    *
    * Instead of copy-pasting the code for creating the set into every test, we
    * can store it in the test class using a val:
    *
    * val s1 = singletonSet(1)
    *
    * However, what happens if the method "singletonSet" has a bug and crashes?
    * Then the test methods are not even executed, because creating an instance
    * of the test class fails!
    *
    * Therefore, we put the shared values into a separate trait (traits are like
    * abstract classes), and create an instance inside each test method.
    */

  trait TestSets:
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

    /** This test is currently disabled (by using .ignore) because the method
      * "singletonSet" is not yet implemented and the test would fail.
      *
      * Once you finish your implementation of "singletonSet", remove the
      * .ignore annotation.
      */

    test("singleton set one contains one") {

      /** We create a new instance of the "TestSets" trait, this gives us access
        * to the values "s1" to "s3".
        */
      new TestSets:
        /** The string argument of "assert" is a message that is printed in case
          * the test fails. This helps identifying which assertion failed.
          */
        assert(contains(s1, 1), "Singleton 1")
        assert(contains(s2, 2), "Singleton 2")
        assert(contains(s3, 3), "Singleton 3")
    }

  test("union contains all elements of each set") {
    new TestSets:
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
  }

  test("interesect does not contain any elements of the union of singletons") {
    new TestSets:
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "Intersect 1")
      assert(!contains(s, 2), "Interesect 2")
      assert(!contains(s, 3), "Intersect 3")
  }

  test("diff contains a singleton when diffed with another insgleton") {
    new TestSets:
      val s = diff(s1, s2)
      assert(contains(s, 1), "Diff 1")
      assert(!contains(s, 2), "Diff 2")
  }

  test("filter returns the subset") {
    new TestSets:
      val s = oddSet
      assert(contains(s, 2), "oddSet 1")
      assert(!contains(s, 3), "oddSet 2")
      assert(!contains(filter(s, x => x % 2 != 0), 2), "oddSet3")
  }

  test("forall returns true when all elements satisfy the predicate") {
    new TestSets:
      val s = oddSet
      assert(forall(s, x => x % 2 == 0), "forall 1")
      assert(!forall(s, x => x % 2 != 0), "forall 2")
  }

  test(
    "exists returns true when at least one element satisfies the predicate"
  ) {
    new TestSets:
      val s = oddSet
      assert(exists(s, x => x % 2 == 0), "exists 1")
      assert(!exists(s, x => x % 2 != 0), "exists 2")
  }

  test("map returns a set transformed by the function") {
    new TestSets:
      val s = oddSet
      val squared = map(s, x => Math.pow(x, 2).intValue)
      assert(contains(squared, 16), "map 1")
      assert(!contains(squared, 15), "map 2")
  }

  override val munitTimeout = 10.seconds
