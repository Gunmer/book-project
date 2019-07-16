package com.gunmer.book_vertx.api

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.MongoClient
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import java.util.logging.Logger

class BookVerticle(private val router: Router, private val mongoClient: MongoClient) : AbstractVerticle() {

  private val log = Logger.getLogger(this.javaClass.simpleName)

  override fun start(startFuture: Future<Void>?) {
    super.start(startFuture)

    router.get("/book").handler(this::findBookByIsbn)
  }

  private fun findBookByIsbn(ctx: RoutingContext) {
    val isbn = ctx.queryParam("isbn").first()
    log.info("ISBN: $isbn")

    val query = JsonObject().put("isbn", isbn)
    log.info("Query: $query")

    mongoClient.find("books", query) { result ->
      log.info("Result is ${result.succeeded()}")
      if (result.succeeded()) {
        log.info("Numbers of results: ${result.result().size}")
        val response = ctx.response()
        response.putHeader("content-type", "application/json")
        response.end(result.result().toString())
      } else {
        ctx.response().end(result.cause().localizedMessage)
      }
    }
  }

}
