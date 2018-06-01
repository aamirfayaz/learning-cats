package chapter3

import cats.Functor

import scala.concurrent.{ExecutionContext, Future}

object Example5 extends App {

  implicit val optionFunctor = new Functor[Option] {
    override def map[A, B](fa: Option[A])(f: A => B): Option[B] ={
      println("I am Fire .......... ")
      fa.map(f)
    }
  }

  val option: Option[Int] = Functor[Option].map(Some(9))(_ * 3)

  implicit def futureFunctor(implicit ec: ExecutionContext) = new Functor[Future] {
    override def map[A, B](fa: Future[A])(f: A => B): Future[B] = {
      println("Future is on Fire .......... ")
      fa.map(f)
    }
  }

  import scala.concurrent.ExecutionContext.Implicits.global

  val future: Future[Int] = Functor[Future].map(Future.successful(4))(_ * 8)
}
