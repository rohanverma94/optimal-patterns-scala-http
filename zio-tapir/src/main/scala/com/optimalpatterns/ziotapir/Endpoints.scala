package com.optimalpatterns.ziotapir

import sttp.tapir._

import Library._
import sttp.tapir.Codec.JsonCodec
import sttp.tapir.generic.auto._
import sttp.tapir.json.zio._
import sttp.tapir.ztapir.ZServerEndpoint
import zio.ZIO
import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder}

object Endpoints {
  case class User(name: String) extends AnyVal
  val helloEndpoint: PublicEndpoint[User, Unit, String, Any] = endpoint.get
    .in("hello")
    .in(query[User]("name"))
    .out(stringBody)
  val helloServerEndpoint: ZServerEndpoint[Any, Any] = helloEndpoint.serverLogicSuccess(user => ZIO.succeed(s"Hello ${user.name}"))

  implicit val authorZioEncoder: zio.json.JsonEncoder[Author] = DeriveJsonEncoder.gen[Author]
  implicit val authorZioDecoder: zio.json.JsonDecoder[Author] = DeriveJsonDecoder.gen[Author]
  implicit val bookZioEncoder: zio.json.JsonEncoder[Book] = DeriveJsonEncoder.gen[Book]
  implicit val bookZioDecoder: zio.json.JsonDecoder[Book] = DeriveJsonDecoder.gen[Book]
  val booksListing: PublicEndpoint[Unit, Unit, List[Book], Any] = endpoint.get
    .in("books" / "list" / "all")
    .out(jsonBody[List[Book]])
  val booksListingServerEndpoint: ZServerEndpoint[Any, Any] = booksListing.serverLogicSuccess(_ => ZIO.succeed(Library.books))

  val apiEndpoints: List[ZServerEndpoint[Any, Any]] = List(helloServerEndpoint, booksListingServerEndpoint)

  val all: List[ZServerEndpoint[Any, Any]] = apiEndpoints
}

object Library {
  case class Author(name: String)
  case class Book(title: String, year: Int, author: Author)

  val books = List(
    Book("The Sorrows of Young Werther", 1774, Author("Johann Wolfgang von Goethe")),
    Book("On the Niemen", 1888, Author("Eliza Orzeszkowa")),
    Book("The Art of Computer Programming", 1968, Author("Donald Knuth")),
    Book("Pharaoh", 1897, Author("Boleslaw Prus"))
  )
}
