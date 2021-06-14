package io.fs.marvel.util

import io.fs.marvel.common.net.rx.RetryWithDelay
import io.fs.marvel.net.Resource
import io.fs.marvel.net.model.OffsetData
import io.fs.marvel.net.model.Response
import io.reactivex.Observable
import java.io.IOException
import java.util.concurrent.TimeUnit

fun <T> Observable<Response<OffsetData<T>>>.toResource(): Observable<Resource<T>> = map { response ->
    val code = response.code ?: 400
    if (code in 200..399) {
        return@map Resource.Success(response.data?.results)
    }
    return@map Resource.Failure<T>(IOException(response.status))
}.retryWith()

fun <T> Observable<T>.retryWith(max: Int = 3, delay: Long = 3L, unit: TimeUnit = TimeUnit.SECONDS): Observable<T> = retryWhen(RetryWithDelay(max, delay, unit))