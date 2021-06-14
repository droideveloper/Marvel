package io.fs.marvel.net.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OffsetData<T>(
        val offset: Int? = null,
        val limit: Int? = null,
        val total: Int? = null,
        val count: Int? = null,
        val results: T? = null)