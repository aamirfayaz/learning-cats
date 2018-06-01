package chapter3.blog

import cats.Contravariant

object Example2 extends App {

  /*
   def contramap[A, B](pred: A => Boolean)(f: B => A): B => Boolean = (b: B) => pred(f(b))

  case class Predicate[A](run: A => Boolean) {
    def contramap[B](f: B => A): Predicate[B] = Predicate( b => run(f(b)))
  }
   */

  type Predicate[A] = A => Boolean

  val predicates = new Contravariant[Predicate] {
    override def contramap[A, B](fa: Predicate[A])(f: B => A): Predicate[B] =
      b => fa(f(b))
  }

  val pred: Predicate[Int] = x => x > 3
  val some = predicates.contramap[Int, String](pred)(_.length)
  println(some("james"))

}
