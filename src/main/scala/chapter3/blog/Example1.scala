package chapter3.blog

/**
  * https://typelevel.org/blog/2016/02/04/variance-and-functors.html
  */

object Example1 extends App {

  sealed trait Shape
  case class Circle() extends Shape
  case class Square() extends Shape

  trait Read[+A] {
    def read(s: String): Option[A]
  }

  trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }

  val listFunctor = new Functor[List] {
    override def map[A, B](fa: List[A])(f: A => B): List[B] = fa match {
      case Nil => Nil
      case h :: t => f(h) :: map(t)(f)
    }
  }

  val readFunctor = new Functor[Read] {
    override def map[A, B](fa: Read[A])(f: A => B): Read[B] = new Read[B] {
      override def read(s: String): Option[B] = fa.read(s) match {
        case Some(a) => Some(f(a))
        case None => None
      }
    }
  }

  val circles: List[Circle] = List(Circle(), Circle(), Circle(), Circle(), Circle())
  val shapes: Seq[Shape] = listFunctor.map(circles)(circle => circle: Shape)

  val ints = List(1, 2, 3, 4, 5, 6)
  listFunctor.map(ints)(_.toString)

  def upcast[F[_], A, B <: A](functor: Functor[F], fb: F[B]) =
    functor.map(fb)(b => b: A)
}
