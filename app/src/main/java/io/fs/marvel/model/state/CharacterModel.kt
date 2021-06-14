package io.fs.marvel.model.state

import io.fs.marvel.model.data.Character
import io.fs.marvel.model.data.Comic
import org.fs.architecture.mvi.common.Model
import org.fs.architecture.mvi.common.SyncState

data class CharacterModel(
        override val state: SyncState,
        override val data: Character,
        val comics: List<Comic> = emptyList()): Model<Character>(state, data)