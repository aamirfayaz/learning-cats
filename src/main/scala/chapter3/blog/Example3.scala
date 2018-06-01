package chapter3.blog

// https://scalerablog.wordpress.com/2017/07/17/abstract-alge-what-functors-and-cucumbers/

object Example3 extends App {

  trait Contravariant[F[_]] {
    def contramap[A, B](fa: F[A])(f: B => A): F[B]
  }

  type Comparison = Int
  val Greater = 1
  val Equal = 0
  val Lower = -1

  trait Comparator[T] {
    def compare(t1: T, t2: T): Comparison
  }

  val comparatorF = new Contravariant[Comparator] {
    override def contramap[A, B](fa: Comparator[A])(f: B => A): Comparator[B] = new Comparator[B] {
      override def compare(t1: B, t2: B): Comparison = fa.compare(f(t1), f(t2))
    }
  }

  case class Cucumber(length: Int)
  val intC: Comparator[Int] = (t1: Int, t2: Int) => t1.compareTo(t2)
  val cucumberToInt = (cucumber: Cucumber) => cucumber.length

  val cucumbersC: Comparator[Cucumber] = comparatorF.contramap(intC)(cucumberToInt)

  val result: Comparison = cucumbersC.compare(Cucumber(12), Cucumber(9))
  println(result)
}
