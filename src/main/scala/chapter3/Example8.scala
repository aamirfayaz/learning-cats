package chapter3

object Example8 extends App {

  trait Printable[A] {
    self =>

    def format(value: A): String

    def contramap[B](f: B => A): Printable[B] = new Printable[B] {
      override def format(value: B): String = self.format(f(value))
    }
  }

  implicit val stringPrintable = new Printable[String] {
    override def format(value: String): String = s"-- $value --"
  }

  implicit val booleanPrintable = new Printable[Boolean] {
    override def format(value: Boolean): String = if(value) "Yes" else "No"
  }

  implicit class PrintableSyntax[T](value: T) {
    def print(implicit printable: Printable[T]) = println(printable.format(value))
  }

  case class Box[T](value: T)

  implicit def boxPrintable[T](implicit printable: Printable[T]) =
    printable.contramap[Box[T]](box => box.value)

  Box("James").print

  def contramap[A, B](pred: A => Boolean)(f: B => A): B => Boolean = (b: B) => pred(f(b))

  case class Predicate[A](run: A => Boolean) {
    def contramap[B](f: B => A): Predicate[B] = Predicate( b => run(f(b)))
  }
}
