package chapter1

// Define a very simple JSON Abstract Syntax Tree (AST)
sealed trait Jsonn
final case class JsObjectt(get: Map[String, Jsonn]) extends Jsonn
final case class JsStringg(get: String) extends Jsonn
final case class JsNumberr(get: Double) extends Jsonn

// The serialize to JSON behaviour is encoded in this trait
trait JsonnWriter[A] {
  def write(value: A): Jsonn
}

// Create Type class instances
final case class Personn(name: String, email: String)

object JsonnWriterInstances {
  implicit val stringJsonWriter = new JsonnWriter[String] {
    override def write(value: String): Jsonn = JsStringg(value)
  }

  implicit val personJsonWriter = new JsonnWriter[Personn] {
    override def write(value: Personn): Jsonn = JsObjectt(
      Map("name" -> JsStringg(value.name), "email" -> JsStringg(value.email))
    )
  }
}

// Pimp my library
object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonnWriter[A]): Jsonn = w.write(value)
  }
}

object Example2 extends App {
  import JsonnWriterInstances._
  import JsonSyntax._

  println(Personn("James", "007@bond.com").toJson)
}