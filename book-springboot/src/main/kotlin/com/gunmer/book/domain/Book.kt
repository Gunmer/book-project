package com.gunmer.book.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("books")
class Book(
        @Id
        val id: String,
        val title: String,
        val authors: List<String>,
        val categories: List<String>,
        val isbn: String,
        val pageCount: Int,
        val status: Status,
        val thumbnailUrl: String?,
        val shortDescription: String?,
        val longDescription: String?,
        val publishedDate: Date?
)
