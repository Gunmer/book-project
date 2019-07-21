package com.gunmer.book.domain.interactors

import com.gunmer.book.data.BookRepository
import io.vertx.config.ConfigRetriever
import io.vertx.core.Handler
import io.vertx.core.eventbus.Message
import io.vertx.core.json.Json
import io.vertx.ext.mongo.MongoClient
import io.vertx.kotlin.config.getConfigAwait
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.logging.Logger

class FindBookByIsbnInteractor : CoroutineVerticle(), Handler<Message<String>> {

  private val log = Logger.getLogger(this.javaClass.simpleName)
  private lateinit var bookRepository: BookRepository

  override suspend fun start() {
    super.start()

    val retriever = ConfigRetriever.create(vertx)
    val mongoDbConfig = retriever.getConfigAwait().getJsonObject("mongoDb")
    val mongoClient = MongoClient.createShared(vertx, mongoDbConfig)

    bookRepository = BookRepository(mongoClient)

    log.info("Start FindBookByIsbnInteractor")
    vertx.eventBus().consumer("findBooksByIsbn", this::handle)
  }

  override fun handle(message: Message<String>?) {
    GlobalScope.launch(vertx.dispatcher()) {
      val isbn = message!!.body()
      log.info("Find by ISBN: $isbn")

      val books = bookRepository.findBooksByIsbn(isbn)

      log.info("${books.size} Books founded")

      val buffer = Json.encodeToBuffer(books)

      message.reply(buffer)
    }
  }

}
