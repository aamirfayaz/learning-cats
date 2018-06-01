package chapter3

import cats.Functor
import cats.syntax.functor._
import cats.instances.option._
import cats.instances.list._

object Example4 extends App {

  def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]) =
    start.map(n => n + 1 * 2)

  println(s" Option(20): ${doMath(Option(20))}")
//  println(s" Some(20): ${doMath(Some(20))}") // this will generate compile time error

  println(s" List(3, 4, 5, 6, 6): ${doMath(List(3, 4, 5, 6, 6))}")
}
