package io.fs.marvel.net

sealed class Resource<out T> {

    data class Success<T>(val data: T?): Resource<T>()
    data class Failure<T>(val error: Throwable?): Resource<T>()
}