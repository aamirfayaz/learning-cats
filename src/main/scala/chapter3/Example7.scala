package chapter3

object Example7 extends App {

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

  "James".print

  true.print
}
