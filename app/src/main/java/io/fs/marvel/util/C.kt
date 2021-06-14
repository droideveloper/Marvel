package io.fs.marvel.util

import io.fs.marvel.BuildConfig

sealed class C {

    sealed class Endpoints {

        companion object {

            private const val PREFIX = "${BuildConfig.API_VERSION}${BuildConfig.API_PREFIX}"

            const val CHARACTER_ID = "characterId"
            const val API_KEY = "apiKey"

            const val OFFSET = "offset"
            const val LIMIT = "limit"

            const val DEFAULT_LIMIT = 30

            const val CHARACTERS = "$PREFIX/characters"
            const val CHARACTER_DETAIL = "$CHARACTERS/{$CHARACTER_ID}"
            const val CHARACTER_COMICS = "$CHARACTER_DETAIL/comics"
        }
    }
}