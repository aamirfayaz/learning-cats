package chapter2.usecases

object Example3Semigroup extends App with Data {

  trait Addable[T] {
    def add(a: T, b: T): T
  }

  implicit val addMoney = new Addable[Money] {
    override def add(m1: Money, m2: Money): Money =
      Money((m1.dollars + m2.dollars) + (m1.cents + m2.cents) / 100,
      (m1.cents + m2.cents) % 100)
  }

  implicit val addInt = new Addable[Int] {
    override def add(a: Int, b: Int): Int = a + b
  }

  implicit def addMap[K,V: Addable] = new Addable[Map[K, V]] {
    override def add(a: Map[K, V], b: Map[K, V]): Map[K, V] = {
      a.foldLeft(b) {
        case (acc, (x, y)) =>
          acc + (x -> acc.get(x).fold(y)(implicitly[Addable[V]].add(_ , y)))
      }
    }
  }

  def add[A: Addable](a: A, b: A)(implicit addable: Addable[A]): A = addable.add(a, b)

  println(s" Salary transfer to all employees ${add(balances, salaries)}")
  println(s" Your marble balance is ${add(marbles, won)}")
}
