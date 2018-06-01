package chapter3

import cats.Functor
import cats.instances.list._
import cats.instances.option._

object Example3 extends App {

  val list = List(1, 2, 3, 4, 5)
  val result1 = Functor[List].map(list)(_.toString)
  println(s"Result1: $result1")

  val option = Some(123)
  val result2 = Functor[Option].map(option)(_.toString)
  println(s"Result1: $result2")

  val func: Int => Int = (x: Int) => x + 1
  val liftFunc: List[Int] => List[Int] = Functor[List].lift(func)
  println(s"LiftFunc: ${liftFunc(List(1, 2, 4, 6, 7, 8))}")
}
