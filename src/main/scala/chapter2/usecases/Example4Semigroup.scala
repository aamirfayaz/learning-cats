package chapter2.usecases

import cats.Semigroup

object Example4Semigroup extends App with Data {

  implicit val moneySemigroup = new Semigroup[Money] {
    override def combine(x: Money, y: Money): Money = {
      Money(x.euro + y.euro + ((x.cents + y.cents) / 100),
        (x.cents + y.cents) % 100)
    }
  }

  import cats.instances.int._
  import cats.instances.map._

  def add[A: Semigroup](a: A, b: A): A = implicitly[Semigroup[A]].combine(a, b)

  println(s"Salary credit in you account xxxxxxx ${add(balance, salary)}")
  println(s"Salary transfer to all employees ${add(balances, salaries)}")
  println(s"Your game marbles balance is: ${add(marbles, won)}")

  import cats.syntax.semigroup._

  println(s"Salary transfer to all employees ${balances |+| salaries}")
  println(s"Your game marbles balance is: ${marbles |+| won}")
}
