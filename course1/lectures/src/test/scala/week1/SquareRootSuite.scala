package week1

class SquareRootSuite extends munit.FunSuite {
  test("sqrt(2) is about 1.4142135623746899") {
    assertEquals(sqrt(2), 1.4142135623746899)
  }

  test("sqrt(4) is about 2.0") {
    assertEqualsDouble(sqrt(4), 2.0, 1e-6)
  }

  test("sqrt(1e-6) is about 0.001") {
    assertEqualsDouble(sqrt(1e-6), 0.001, 1e-3)
  }

  test("sqrt(0) is 0.0") {
    assert(clue(sqrt(0)) == clue(0.0))
  }
}