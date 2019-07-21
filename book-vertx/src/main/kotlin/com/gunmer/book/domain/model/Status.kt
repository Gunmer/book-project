package com.gunmer.book.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

enum class Status {
  @JsonProperty
  PUBLISH,
  @JsonProperty
  MEAP
}
