package com.gunmer.book_vertx

import com.gunmer.book_vertx.api.BookVerticle
import io.vertx.config.ConfigRetriever
import io.vertx.core.AbstractVerticle
import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.MongoClient
import io.vertx.ext.web.Router
import java.util.logging.Logger

class MainVerticle : AbstractVerticle() {

  private val log = Logger.getLogger(this.javaClass.simpleName)

  override fun start(startFuture: Future<Void>) {
    super.start(startFuture)

    val retriever = ConfigRetriever.create(vertx)
    retriever.getConfig(this::configApp)
  }

  private fun configApp(configResult: AsyncResult<JsonObject>) {
    val router = Router.router(vertx)
    val config = configResult.result()

    vertx.createHttpServer(HttpServerOptions(config))
      .requestHandler(router::accept)
      .listen(config.getInteger("server_port", 8080))

    val mongoClient = MongoClient.createShared(vertx, config)
    vertx.deployVerticle(BookVerticle(router, mongoClient))
  }
}
