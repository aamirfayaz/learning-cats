package chapter1

object Example2 extends App {

  // Define a very simple JSON Abstract Syntax Tree (AST)
  sealed trait Json
  final case class JsObject(get: Map[String, Json]) extends Json
  final case class JsString(get: String) extends Json
  final case class JsNumber(get: Double) extends Json

  // The serialize to JSON behaviour is encoded in this trait
  trait JsonWriter[A] {
    def write(value: A): Json
  }

  // Create Type class instances
  final case class Person(name: String, email: String)

  object JsonWriterInstances {
    implicit val stringJsonWriter = new JsonWriter[String] {
      override def write(value: String): Json = JsString(value)
    }

    implicit val personJsonWriter = new JsonWriter[Person] {
      override def write(value: Person): Json = JsObject(
        Map("name" -> JsString(value.name), "email" -> JsString(value.email))
      )
    }
  }

  // Pimp my library
  object JsonSyntax {
    implicit class JsonWriterOps[A](value: A) {
      def toJson(implicit w: JsonWriter[A]): Json = w.write(value)
    }
  }

  import JsonWriterInstances._
  import JsonSyntax._

  println(Person("James", "007@bond.com").toJson)
}