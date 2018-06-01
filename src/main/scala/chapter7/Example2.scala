package chapter7

import cats.Foldable
import cats.instances.list._
import cats.instances.option._

object Example2 extends App {

  // scala foldLeft
  val scalaFoldLeft = List(1, 2, 3, 4, 5).foldLeft(0)(_ + _)

  // scala-cats foldLeft
  val scalaCatsFoldLeft = Foldable[List].foldLeft(List(1, 2, 3, 4, 5), 0)(_ + _)

  assert(scalaFoldLeft == scalaCatsFoldLeft)

  //scala foldLeft with Option
  val scalaFoldLeftOption = Option(9).foldLeft(10)(_ * _)

  // scala cats foldLeft with Option
  val scalaCatsFoldLeftOption = Foldable[Option].foldLeft(Option(9), 10)(_ * _)

  assert(scalaFoldLeftOption == scalaCatsFoldLeftOption)
}
