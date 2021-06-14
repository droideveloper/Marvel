package io.fs.marvel.model.intent

import io.fs.marvel.common.repo.CharacterRepository
import io.fs.marvel.util.C.Endpoints.Companion.DEFAULT_LIMIT
import io.fs.marvel.util.Operations.Companion.LOAD

class LoadCharactersIntent(charactersRepository: CharacterRepository): AbsCharactersIntent(offset = 0, limit = DEFAULT_LIMIT, charactersRepository) {

    override val state: Int get() = LOAD
}