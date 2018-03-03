package chapter1

object Example9 extends App {

  final case class Cat(
                        name: String,
                        age: Int,
                        color: String
                      )

  import cats.Eq

  object CatInstances {
    import cats.instances.all._
    import cats.syntax.eq._

    implicit val catInstances = Eq.instance[Cat] { (cat1, cat2) =>
      {
        (cat1.name === cat2.name) &&
        (cat1.age === cat2.age) &&
        (cat1.color === cat2.color)
      }
    }
  }

  val cat1 = Cat("Garfield",   38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")

  import CatInstances._

  val eqCat = Eq[Cat]
  println(eqCat.eqv(cat1, cat2))
}
