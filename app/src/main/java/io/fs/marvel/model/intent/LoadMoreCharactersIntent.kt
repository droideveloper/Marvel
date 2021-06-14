package io.fs.marvel.model.intent

import io.fs.marvel.common.repo.CharacterRepository
import io.fs.marvel.util.C.Endpoints.Companion.DEFAULT_LIMIT
import io.fs.marvel.util.Operations.Companion.LOAD_MORE

class LoadMoreCharactersIntent(offset: Int, charactersRepository: CharacterRepository): AbsCharactersIntent(offset, limit = DEFAULT_LIMIT, charactersRepository) {

    override val state: Int get() = LOAD_MORE
}