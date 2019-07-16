package com.gunmer.book.api.controllers

import com.gunmer.book.domain.Book
import com.gunmer.book.domain.repositories.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController("/book")
class BookController {

    @Autowired
    private lateinit var bookRepository: BookRepository

    @GetMapping
    fun findByISBN(@RequestParam isbn: String): List<Book> = bookRepository.findAllByIsbn(isbn)

}
