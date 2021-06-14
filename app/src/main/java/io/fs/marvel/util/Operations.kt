package io.fs.marvel.util

sealed class Operations {

    companion object {
        const val LOAD = 0x01
        const val LOAD_MORE = 0x02
    }
}