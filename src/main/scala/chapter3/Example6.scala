package chapter3

import cats.Functor

object Example6 extends App {

  sealed trait Tree[+T]
  final case class Branch[T](left: Tree[T], right: Tree[T]) extends Tree[T]
  final case class Leaf[T](value: T) extends Tree[T]

  object Tree {
    def branch[T](left: Tree[T], right: Tree[T]): Tree[T] = Branch(left, right)
    def leaf[T](value: T): Tree[T] = Leaf(value)
  }

  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] =
      fa match {
        case Branch(left, right) => Branch(map(left)(f), map(right)(f))
        case Leaf(value) => Leaf(f(value))
      }
  }

  val tree = Functor[Tree].map(Tree.leaf(100))(_ * 3)
  println(s"Tree: $tree")

}
