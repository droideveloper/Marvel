package io.fs.marvel.model.intent

import org.fs.architecture.mvi.common.ReducerIntent

class NothingIntent<T>: ReducerIntent<T>() {

    override fun invoke(o: T): T = o
}