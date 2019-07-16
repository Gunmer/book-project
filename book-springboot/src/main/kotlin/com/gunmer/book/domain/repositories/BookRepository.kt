package com.gunmer.book.domain.repositories

import com.gunmer.book.domain.Book
import org.springframework.data.mongodb.repository.MongoRepository

interface BookRepository : MongoRepository<Book, String> {
    fun findAllByIsbn(isbn: String): List<Book>
}