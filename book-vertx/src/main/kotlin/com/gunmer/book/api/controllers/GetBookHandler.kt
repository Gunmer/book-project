package com.gunmer.book.api.controllers

import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.core.buffer.Buffer
import io.vertx.ext.web.RoutingContext
import java.util.logging.Logger

class GetBookHandler(private val vertx: Vertx) : Handler<RoutingContext> {

  private val log = Logger.getLogger(this.javaClass.simpleName)

  override fun handle(ctx: RoutingContext) {
    val isbn = ctx.queryParam("isbn").first()
    log.info("Get /book?isbn=$isbn")

    vertx.eventBus().send<Buffer>("findBooksByIsbn", isbn) {
      val response = ctx.response()
      if (it.succeeded()) {
        val buffer = it.result().body()
        response.putHeader("content-type", "application/json")
        response.end(buffer)
      } else {
        response.setStatusCode(500).end(it.cause().localizedMessage)
      }
    }

  }

}
