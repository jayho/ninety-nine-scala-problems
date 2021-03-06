/**
  * Exercises: http://aperiodic.net/phil/scala/s-99/#btrees
  */
sealed abstract class Tree[+T] {
  // P56
  def isSymmetric: Boolean = ???

  // P58a
  def addValue[U >: T](x: U)(implicit o: U => Ordered[U]): Tree[U] = ???

  // P59a
  def height: Int = ???

  // P60a
  def nodeCount: Int = ???

  // P61a
  def leafList: List[T] = ???

  // P61b
  def leafCount: Int = ???

  // P62a
  def internalList: List[T] = ???

  // P62b
  def atLevel(n: Int): List[T] = ???

  // P64
  def layoutBinaryTree: Tree[T] = ???

  // P65
  def layoutBinaryTree2: Tree[T] = ???

  // P66
  def layoutBinaryTree3: Tree[T] = ???

  // P68a
  def preorder: List[T] = ???

  // P68b
  def inorder: List[T] = ???

  // P69
  def toDotString: String = ???
}

case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T] {
  override def toString = s"T($value $left $right)"
}

case object End extends Tree[Nothing] {
  override def toString = "."
}

case class PositionedNode[+T](value: T, left: Tree[T], right: Tree[T], x: Int, y: Int) extends Tree[T] {
  override def toString: String = s"T[$x,$y]($value $left $right)"
}

object Tree {
  // P55
  def completelyBalancedTrees[T](nodes: Int, value: T): List[Tree[T]] = ???

  // P57
  def symmetricBalancedTrees[T](nodes: Int, value: T): List[Tree[T]] = ???

  // P58b
  def fromList[T](l: List[T])(implicit o: T => Ordered[T]): Tree[T] = ???

  // P59b
  def heightBalancedTreesWithHeight[T](height: Int, value: T): List[Tree[T]] = ???

  // P60b
  def maxHbalNodes(height: Int): Int = ???

  // P60c
  def minHbalNodes(height: Int): Int = ???

  // P60d
  def maxHbalHeight(nodes: Int): Int = ???

  // P60e
  def minHbalHeight(nodes: Int): Int = ???

  // P60f
  def heightBalancedTreesWithNodes[T](nodes: Int, value: T): List[Tree[T]] = ???

  // P63
  def completeBinaryTree[T](nodes: Int, value: T): Tree[T] = ???

  // P67b
  def fromString(s: String): Tree[String] = ???

  // P68c
  def preInTree[T](preorder: List[T], inorder: List[T]): Tree[T] = ???

  // P69
  def fromDotString(s: String): Tree[String] = ???
}

object Node {
  def apply[T](value: T): Node[T] = Node(value, End, End)
}

object PositionedNode {
  def apply[T](value: T, x: Int, y: Int): PositionedNode[T] = PositionedNode(value, End, End, x, y)
}

