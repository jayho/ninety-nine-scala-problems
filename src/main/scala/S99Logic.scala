/**
  * Exercises: http://aperiodic.net/phil/scala/s-99/#logic
  *
  * For the logic exercises, avoid using builtin functions. Define `not`, `and`, `or` in terms of pattern matching, and
  * other functions in terms of those three.
  */
class S99Logic(val b: Boolean) {

  import S99Logic._

  // P47
  def and(y: Boolean): Boolean = (b, y) match {
    case (true, true) => true
    case _ => false
  }

  def or(y: Boolean): Boolean = (b, y) match {
    case (_, true) => true
    case (true, _) => true
    case _ => false
  }

  def nand(y: Boolean): Boolean = not(b and y)

  def nor(y: Boolean): Boolean = not(b or y)

  def xor(y: Boolean): Boolean = (b or y) and (b nand y)

  def impl(y: Boolean): Boolean = not(b) or y

  def equ(y: Boolean): Boolean = not(b xor y)
}

object S99Logic {
  implicit def boolean2S99Logic(b: Boolean): S99Logic = new S99Logic(b)

  // P46a
  def and(x: Boolean, y: Boolean): Boolean = (x, y) match {
    case (true, true) => true
    case _ => false
  }

  def or(x: Boolean, y: Boolean): Boolean = (x, y) match {
    case (_, true) => true
    case (true, _) => true
    case _ => false
  }

  def nand(x: Boolean, y: Boolean): Boolean = not(and(x, y))

  def nor(x: Boolean, y: Boolean): Boolean = not(or(x, y))

  def xor(x: Boolean, y: Boolean): Boolean = and(or(x, y), nand(x, y))

  def impl(x: Boolean, y: Boolean): Boolean = or(not(x), y)

  def equ(x: Boolean, y: Boolean): Boolean = not(xor(x, y))

  // P46b
  def table2(f: (Boolean, Boolean) => Boolean): Map[(Boolean, Boolean), Boolean] = {
    for {
      x <- List(false, true)
      y <- List(false, true)
    } yield (x, y) -> f(x, y)
  }.toMap

  // P47
  def not(x: Boolean): Boolean = x match {
    case false => true
    case true => false
  }

  // P49
  def gray(n: Int): List[String] = {
    require(n >= 1)
    def grayR(n: Int, g: List[String]): List[String] =
      if (n == 1) g
      else grayR(n - 1, (g map ("0" + _)) ++ (g.reverse map ("1" + _)))

    grayR(n, List("0", "1"))
  }

  // P50
  private abstract sealed class Tree[T](val freq: Int)

  private final case class Node[T](left: Tree[T], right: Tree[T]) extends Tree[T](left.freq + right.freq)

  private final case class Leaf[T](override val freq: Int, elem: T) extends Tree[T](freq)

  def huffman[T](frequencies: List[(T, Int)]): List[(T, String)] = {
    require(frequencies.size > 1)
    def buildTreeR(fs: List[Tree[T]]): Tree[T] =
      fs.sortBy(_.freq) match {
        case t1 +: t2 +: ts => buildTreeR(Node(t1, t2) +: ts)
        case t +: Nil => t
      }

    def listCodesR(t: Tree[T], code: String): List[(T, String)] = t match {
      case Leaf(_, s) => List((s, code))
      case Node(l, r) => listCodesR(l, code + "0") ++ listCodesR(r, code + "1")
    }

    listCodesR(buildTreeR(frequencies map { case (s, f) => Leaf(f, s) }), "")
  }
}
