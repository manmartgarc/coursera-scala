package patmat

class HuffmanSuite extends munit.FunSuite:
  import Huffman.*

  trait TestTrees {
    val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)
    val t2 = Fork(
      Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5),
      Leaf('d', 4),
      List('a', 'b', 'd'),
      9
    )
  }

  test("weight of a larger tree (10pts)") {
    new TestTrees:
      assertEquals(weight(t1), 5)
  }

  test("chars of a larger tree (10pts)") {
    new TestTrees:
      assertEquals(chars(t2), List('a', 'b', 'd'))
  }

  test("string2chars hello world") {
    assertEquals(
      string2Chars("hello, world"),
      List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd')
    )
  }

  test("times of some examples") {
    assertEquals(
      times(List('a', 'b', 'a')),
      List(('a', 2), ('b', 1))
    )
    assertEquals(
      times(List('a', 'b', 'a', 'c', 'a', 'b')),
      List(('a', 3), ('b', 2), ('c', 1))
    )
  }

  test("singleton true") {
    new TestTrees:
      assertEquals(singleton(List(t1)), true)
      val singletonTree =
        List(Fork(Leaf('a', 1), Leaf('b', 2), List('a', 'b'), 3))
  }

  test("make ordered leaf list for some frequency table (15pts)") {
    assertEquals(
      makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))),
      List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3))
    )
  }

  test("combine of some leaf list (15pts)") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assertEquals(
      combine(leaflist),
      List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4))
    )
  }

  test("createCodeTree should work") {
    assertEquals(
      createCodeTree(string2Chars("huffmanestcool")),
      Fork(
        Fork(
          Fork(Leaf('m', 1), Leaf('n', 1), List('m', 'n'), 2),
          Fork(
            Fork(Leaf('c', 1), Leaf('s', 1), List('c', 's'), 2),
            Fork(Leaf('e', 2), Leaf('f', 1), List('e', 'f'), 3),
            List('c', 's', 'e', 'f'),
            5
          ),
          List('m', 'n', 'c', 's', 'e', 'f'),
          7
        ),
        Fork(
          Fork(Leaf('t', 1), Leaf('u', 1), List('t', 'u'), 2),
          Fork(Leaf('o', 2), Leaf('l', 2), List('o', 'l'), 4),
          List('t', 'u', 'o', 'l'),
          6
        ),
        List('m', 'n', 'c', 's', 'e', 'f', 't', 'u', 'o', 'l'),
        13
      )
    )
  }

  test("decoding French secret") {
    assertEquals(decodedSecret.mkString, "huffmanestcool")
  }

  test("decode and encode a very short text should be identity (10pts)") {
    new TestTrees:
      assertEquals(decode(t1, encode(t1)("ab".toList)), "ab".toList)
  }

  test("codeBits for CodeTable") {
    val codeTable = List(('a', List(0, 1)), ('b', List(1, 0)))
    assertEquals(codeBits(codeTable)('a'), List(0, 1))
    assertEquals(codeBits(codeTable)('b'), List(1, 0))
  }

  test("convert CodeTree to CodeTable") {
    new TestTrees:
      assertEquals(convert(t1), List(('a', List(0)), ('b', List(1))))
      assertEquals(
        convert(t2),
        List(('a', List(0, 0)), ('b', List(0, 1)), ('d', List(1)))
      )
  }

  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
