package chapter1

import cats.Show
import cats.instances.string._
import cats.instances.int._

object Example5 extends App {

  val showInt = Show[Int]
  val showString = Show[String]

  println(showInt.show(1100011))
  println(showString.show("James"))

  import cats.syntax.show._

  println(130013.show)
  println("Jimmy".show)
}
