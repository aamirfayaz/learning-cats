package chapter3

import scala.concurrent.{Await, Future}
import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationDouble

object Example1 extends App {

  val future1 = {
    val r = new Random(0l)
    val x = Future(r.nextInt())

    for {
      a <- x
      b <- x
    } yield (a, b)
  }

  val future2 = {
    val r = new Random(0l)

    for {
      a <- Future(r.nextInt())
      b <- Future(r.nextInt())
    } yield (a, b)
  }

  val result1 = Await.result(future1, 1 seconds)
  val result2 = Await.result(future2, 1 seconds)
  println(result1)
  println(result2)
}
