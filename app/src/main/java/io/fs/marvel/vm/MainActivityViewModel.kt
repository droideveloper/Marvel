package io.fs.marvel.vm

import io.fs.marvel.model.intent.NothingIntent
import io.fs.marvel.model.state.MainModel
import io.fs.marvel.view.MainActivityView
import org.fs.architecture.mvi.common.Event
import org.fs.architecture.mvi.common.ForActivity
import org.fs.architecture.mvi.common.Idle
import org.fs.architecture.mvi.common.Intent
import org.fs.architecture.mvi.core.AbstractViewModel
import org.fs.rx.extensions.util.EMPTY
import javax.inject.Inject

@ForActivity
class MainActivityViewModel @Inject constructor(view: MainActivityView): AbstractViewModel<MainModel, MainActivityView>(view){

    override fun initState(): MainModel = MainModel(state = Idle, data = String.EMPTY)

    override fun toIntent(event: Event): Intent {
        return NothingIntent<MainModel>()
    }
}