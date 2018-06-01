package chapter2.usecases

object Example1Semigroup extends App with Data {


  def add(m1: Money, m2: Money): Money = {
    Money((m1.dollars + m2.dollars) + (m1.cents + m2.cents) / 100,
      (m1.cents + m2.cents) % 100)
  }

  def addSalaryToAccounts(balances: Map[String, Money], salaries: Map[String, Money]): Map[String, Money] = {
    balances.foldLeft(salaries) {
      case (acc, (name, money)) =>
        acc + (name -> acc.get(name).fold(money)(add(_, money)))
    }
  }

  def addMarbles(balances: Map[String, Int], wining: Map[String, Int]): Map[String, Int] = {
    balances.foldLeft(wining) {
      case (acc, (name, marble)) =>
        acc + (name -> acc.get(name).fold(marble)(_ + marble))
    }
  }

  println(s" Salary credit into you account ${add(balance, salary)}")
  println(s" Salary transfer to all employees ${addSalaryToAccounts(balances, salaries)}")
  println(s" Your marble balance is ${addMarbles(marbles, won)}")
}
