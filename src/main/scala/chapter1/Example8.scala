package chapter1

object Example8 extends App {

  import cats.syntax.eq._
  import cats.instances.int._
  import cats.instances.option._
  import cats.syntax.option._

  val result = (Some(1): Option[Int]) === None // need to track why this compile
  println("(Some(1): Option[Int]) === None : " + result)

  println("Option(1) === Option.empty : " + (Option(1) === Option.empty))

  println("1.some === none[Int] : " + (1.some === none[Int]))

  println("1.some =!= none[Int] : " + (1.some =!= none[Int]))
}
