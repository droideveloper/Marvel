package io.fs.marvel.view

import io.fs.marvel.model.state.CharactersModel
import org.fs.architecture.mvi.common.View

interface CharactersFragmentView: View {
    fun render(model: CharactersModel)
}