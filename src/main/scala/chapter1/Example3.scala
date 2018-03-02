package chapter1

// The Type Class
trait Printable[A] {
  def format(value: A): String
}

// The Type Instances
object PrintableInstances {
  implicit val stringInstance = new Printable[String] {
    override def format(value: String): String = value
  }

  implicit val intInstance = new Printable[Int] {
    override def format(value: Int): String = value.toString
  }
}

// Interfaces
object Printable {
  def format[A](value: A)(implicit print: Printable[A]): String = print.format(value)
  def print[A](value: A)(implicit print: Printable[A]): Unit = println(format(value))
}

object Example3 extends App {
  import PrintableInstances._
  import Printable._

  println(s"Format: ${format(10001)}")
  print("James")
}