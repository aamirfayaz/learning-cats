package chapter1

object Example7 extends App {

  import cats.Eq
  import cats.instances.int._

  val eqInt = Eq[Int]
  println(eqInt.eqv(1, 2))

  import cats.syntax.eq._

  println(1 === 1)
  println(1 =!= 2)
}
