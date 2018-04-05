package chapter2.usecases

import cats.Semigroup

object Domain {
  type Product = String
  type Price = Double
  type Number = Int

  val catalog: Map[Product, Price] = Map(
    "cola" -> 2.25,
    "fanta" -> 5.25,
    "appy" -> 1.2
  )

  object Order {

    import cats.implicits._

    implicit val orderSemigroup = new Semigroup[Order] {
      override def combine(x: Order, y: Order): Order = Order(x.item |+| y.item)
    }

    // Combine using the semigroup
    def combineOrder(o1: Order, o2: Order)(implicit semigroup: Semigroup[Order]): Order = {
      semigroup.combine(o1, o2)
    }

    // Implement using recursion
    def times(o1: Order, count: Int)(implicit semigroup: Semigroup[Order]): Order = {
      def loop(o: Order, n: Int): Order = {
        if(n == 1) o
        else loop(semigroup.combine(o, o1), (n - 1))
      }
      loop(o1, count)
    }

    def combineN(o1: Order, times: Int)(implicit semigroup: Semigroup[Order]): Order = {
      semigroup.combineN(o1, times)
    }
  }

  case class Order(item: Map[Product, Number])
}