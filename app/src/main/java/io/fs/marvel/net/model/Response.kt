package io.fs.marvel.net.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response<T>(
    val code: Int? = null,
    val status: String? = null,
    val data: T? = null)