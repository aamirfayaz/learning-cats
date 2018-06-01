package chapter2.usecases

import java.time.LocalDate

import cats.instances.all._
import cats.kernel.Monoid
import cats.syntax.semigroup._

object Example3Monoid extends App {

  sealed abstract class Currency
  case object USD extends Currency
  case object Rupee extends Currency

  final case class TradeTemplate (
     payments: List[LocalDate],
     ccy: Option[Currency],
     otc: Option[Boolean]
   )

  /*object TradeTemplate {
    implicit val tradeMonoid = new Monoid[TradeTemplate] {
      override def empty: TradeTemplate = TradeTemplate(Nil, None, None)

      override def combine(x: TradeTemplate, y: TradeTemplate): TradeTemplate = {
        TradeTemplate(
          x.payments |+| y.payments, x.ccy |+| y.ccy, x.otc |+| y.otc
        )
      }
    }
  }*/

}