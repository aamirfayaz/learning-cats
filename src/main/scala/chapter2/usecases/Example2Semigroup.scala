package chapter2.usecases

object Example2Semigroup extends App with Data {

  def addM(m1: Money, m2: Money): Money = {
    Money((m1.dollars + m2.dollars) + (m1.cents + m2.cents) / 100,
      (m1.cents + m2.cents) % 100)
  }

  trait Addable[T] {
    def add(a: T, b: T): T
  }

  def add[X, Y](balances: Map[X, Y], map: Map[X, Y])(implicit A: Addable[Y]): Map[X, Y] = {
    balances.foldLeft(map) {
      case (acc, (x, y)) =>
        acc + (x -> acc.get(x).fold(y)(A.add(_ , y)))
    }
  }

  implicit val addMoney = new Addable[Money] {
    override def add(a: Money, b: Money): Money = addM(a, b)
  }

  implicit val addInt = new Addable[Int] {
    override def add(a: Int, b: Int): Int = a + b
  }

  println(s" Salary transfer to all employees ${add(balances, salaries)}")
  println(s" Your marble balance is ${add(marbles, won)}")
}


