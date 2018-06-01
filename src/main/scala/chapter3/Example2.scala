package chapter3

import cats.syntax.functor._
import cats.instances.function._

object Example2 extends App {

  val func1: Int => Double = x => x.toDouble
  val func2: Double => Double = x => x * 2

  val result1 = (func1 andThen func2)(1)
  val result2 = func2(func1(1))

  assert(result1 == result2)

  val result3 = (func1 map func2)(1)
  assert(result3 == result2)

  val func = ((x: Int) => x.toDouble) map (x => x + 1) map (x => x * 2) map (x => x + "!")
  println(func(123))

  val func4 = ((x: Int) => x.toDouble) andThen (x => x + 1) andThen (x => x * 2) andThen (x => x + "!")
  println(func4(123))
}
