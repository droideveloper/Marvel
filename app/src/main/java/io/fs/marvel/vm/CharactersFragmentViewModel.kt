package io.fs.marvel.vm

import io.fs.marvel.common.repo.CharacterRepository
import io.fs.marvel.model.event.LoadCharactersEvent
import io.fs.marvel.model.event.LoadMoreCharactersEvent
import io.fs.marvel.model.intent.LoadCharactersIntent
import io.fs.marvel.model.intent.LoadMoreCharactersIntent
import io.fs.marvel.model.intent.NothingIntent
import io.fs.marvel.model.state.CharactersModel
import io.fs.marvel.view.CharactersFragmentView
import org.fs.architecture.mvi.common.Event
import org.fs.architecture.mvi.common.ForFragment
import org.fs.architecture.mvi.common.Idle
import org.fs.architecture.mvi.common.Intent
import org.fs.architecture.mvi.core.AbstractViewModel
import javax.inject.Inject

@ForFragment
class CharactersFragmentViewModel @Inject constructor(
        private val characterRepository: CharacterRepository,
        view: CharactersFragmentView): AbstractViewModel<CharactersModel, CharactersFragmentView>(view) {

    override fun initState(): CharactersModel = CharactersModel(state = Idle, data = emptyList())

    override fun toIntent(event: Event): Intent = when(event) {
        is LoadCharactersEvent -> LoadCharactersIntent(characterRepository)
        is LoadMoreCharactersEvent -> LoadMoreCharactersIntent(event.offset, characterRepository)
        else -> NothingIntent<CharactersModel>()
    }
}