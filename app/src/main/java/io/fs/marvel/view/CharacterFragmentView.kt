package io.fs.marvel.view

import io.fs.marvel.model.state.CharacterModel
import org.fs.architecture.mvi.common.View

interface CharacterFragmentView: View {
    fun render(model: CharacterModel)
}