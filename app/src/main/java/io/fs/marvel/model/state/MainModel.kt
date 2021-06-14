package io.fs.marvel.model.state

import org.fs.architecture.mvi.common.Model
import org.fs.architecture.mvi.common.SyncState

data class MainModel(override val state: SyncState, override val data: String): Model<String>(state, data)