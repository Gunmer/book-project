package com.gunmer.book.api

import com.gunmer.book.api.controllers.GetBookHandler
import io.vertx.config.ConfigRetriever
import io.vertx.core.http.HttpServerOptions
import io.vertx.ext.web.Router
import io.vertx.kotlin.config.getConfigAwait
import io.vertx.kotlin.coroutines.CoroutineVerticle
import java.util.logging.Logger

class ServerVerticle : CoroutineVerticle() {

  private val log = Logger.getLogger(this.javaClass.simpleName)

  override suspend fun start() {
    super.start()
    val router = Router.router(vertx)
    val configRetriever = ConfigRetriever.create(vertx)
    val configServer = configRetriever.getConfigAwait().getJsonObject("server")

    router.get("/book").handler(GetBookHandler(vertx))

    val actualPort = vertx.createHttpServer(HttpServerOptions(configServer))
      .requestHandler(router)
      .listen()
      .actualPort()

    log.info("Run server in port: $actualPort")
  }

}
