package chapter1


object Example4 extends App {

final case class Cat(
                    name: String,
                    age: Int,
                    color: String
                    )

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

  implicit val catInstance = new Printable[Cat] {
    override def format(value: Cat): String =
      s"${value.name} is a ${value.age} yeat-old ${value.color} cat."
  }
}

object PrintableSyntax {
  implicit class PrintOps[A](value: A) {
    def format(implicit p: Printable[A]): String = p.format(value)
    def print(implicit p: Printable[A]): Unit = println(format)
  }
}

  import PrintableInstances._
  import PrintableSyntax._

  Cat("Garfield", 8, "Orange").print
}
