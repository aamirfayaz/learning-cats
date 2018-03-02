package chapter1

final case class Cat(
                    name: String,
                    age: Int,
                    color: String
                    )

// The Type Class
trait Printablee[A] {
  def format(value: A): String
}

// The Type Instances
object PrintableeInstances {
  implicit val stringInstance = new Printablee[String] {
    override def format(value: String): String = value
  }

  implicit val intInstance = new Printablee[Int] {
    override def format(value: Int): String = value.toString
  }

  implicit val catInstance = new Printablee[Cat] {
    override def format(value: Cat): String =
      s"${value.name} is a ${value.age} yeat-old ${value.color} cat."
  }
}

object PrintableeSyntax {
  implicit class PrintOps[A](value: A) {
    def format(implicit p: Printablee[A]): String = p.format(value)
    def print(implicit p: Printablee[A]): Unit = println(format)
  }
}

object Example4 extends App {

  import PrintableeInstances._
  import PrintableeSyntax._

  Cat("Garfield", 8, "Orange").print
}
