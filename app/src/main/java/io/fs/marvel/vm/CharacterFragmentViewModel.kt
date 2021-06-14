package io.fs.marvel.vm

import io.fs.marvel.common.repo.CharacterRepository
import io.fs.marvel.model.data.Character
import io.fs.marvel.model.event.LoadCharacterComicsEvent
import io.fs.marvel.model.intent.LoadCharacterComicsIntent
import io.fs.marvel.model.intent.NothingIntent
import io.fs.marvel.model.state.CharacterModel
import io.fs.marvel.view.CharacterFragmentView
import org.fs.architecture.mvi.common.*
import org.fs.architecture.mvi.core.AbstractViewModel
import javax.inject.Inject

@ForFragment
class CharacterFragmentViewModel @Inject constructor(
        private val characterRepository: CharacterRepository,
        view: CharacterFragmentView): AbstractViewModel<CharacterModel, CharacterFragmentView>(view) {

    override fun initState(): CharacterModel = CharacterModel(state = Idle, data = Character.EMPTY)

    override fun toIntent(event: Event): Intent = when(event) {
        is LoadCharacterComicsEvent -> LoadCharacterComicsIntent(event.characterId, characterRepository)
        else -> NothingIntent<CharacterModel>()
    }
}