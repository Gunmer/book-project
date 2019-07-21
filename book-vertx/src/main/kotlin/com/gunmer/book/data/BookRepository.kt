package com.gunmer.book.data

import com.gunmer.book.domain.model.Book
import io.vertx.core.Future
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.MongoClient
import io.vertx.kotlin.coroutines.await

class BookRepository(private val mongoClient: MongoClient) {

  suspend fun findBooksByIsbn(isbn: String): List<Book> {
    val future = Future.future<List<JsonObject>>()
    val query = JsonObject().put("isbn", isbn)
    mongoClient.find("books", query, future)

    val result = future.await()

    return result.map { it.mapTo(Book::class.java) }
  }

}
