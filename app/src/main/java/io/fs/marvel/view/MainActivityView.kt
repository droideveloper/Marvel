package io.fs.marvel.view

import io.fs.marvel.model.state.MainModel
import org.fs.architecture.mvi.common.View

interface MainActivityView: View {
    fun render(model: MainModel)
}