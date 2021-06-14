package io.fs.marvel.model.state

import io.fs.marvel.model.data.Character
import org.fs.architecture.mvi.common.Model
import org.fs.architecture.mvi.common.SyncState

data class CharactersModel(
        override val state: SyncState,
        override val data: List<Character>): Model<List<Character>>(state, data)