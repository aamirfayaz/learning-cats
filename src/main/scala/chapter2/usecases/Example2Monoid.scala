package chapter2.usecases

object Example2Monoid extends App {

  case class Ticker(id: Int)

  trait TradingPosition {
    def inventoryPnL(implicit prices: Map[Ticker, Double]): Double

    def tradingPnL(implicit prices: Map[Ticker, Double]): Double

    def totalPnL(implicit prices: Map[Ticker, Double]) =
      inventoryPnL -> tradingPnL
  }
}
