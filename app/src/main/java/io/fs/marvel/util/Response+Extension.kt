package io.fs.marvel.util

import io.fs.marvel.net.Resource
import io.fs.marvel.net.model.OffsetData
import io.fs.marvel.net.model.Response
import io.reactivex.Observable
import java.io.IOException

fun <T> Observable<Response<OffsetData<T>>>.toResource(): Observable<Resource<T>> = map { response ->
    val code = response.code ?: 400
    if (code in 200..399) {
        return@map Resource.Success(response.data?.results)
    }
    return@map Resource.Failure<T>(IOException(response.status))
}