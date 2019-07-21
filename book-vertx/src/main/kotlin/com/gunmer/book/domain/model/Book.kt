package com.gunmer.book.domain.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Book(
  val title: String,
  val authors: List<String>,
  val categories: List<String>,
  val isbn: String,
  val pageCount: Int,
  val status: Status,
  val thumbnailUrl: String?,
  val shortDescription: String?,
  val longDescription: String?
)
