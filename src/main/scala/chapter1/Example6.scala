package chapter1

import cats.Show

object Example6 extends App {

  final case class Cat(
                       name: String,
                       age: Int,
                       color: String
                     )

  object CatInstances {
    implicit val catInstance = Show.show[Cat] { cat =>
      s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."
    }
  }

  import CatInstances._

  val catShow = Show[Cat]
  println(catShow.show(Cat("Garfield", 8, "Orange")))

  object CatsPrintableSyntax {
    implicit class ShowOps[A](value: A) {
      def show(implicit s: Show[A]): String = s.show(value)
    }
  }

  import CatsPrintableSyntax._

  println(Cat("Garfield", 10, "Orange").show)

}
