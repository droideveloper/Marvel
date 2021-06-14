package io.fs.marvel.view

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import io.fs.marvel.R
import io.fs.marvel.model.data.Character
import io.fs.marvel.model.data.Comic
import io.fs.marvel.model.event.LoadCharacterComicsEvent
import io.fs.marvel.model.state.CharacterModel
import io.fs.marvel.util.Operations.Companion.LOAD
import io.fs.marvel.util.bindProgress
import io.fs.marvel.util.setUp
import io.fs.marvel.util.showError
import io.fs.marvel.view.adapter.CharacterAdapter
import io.fs.marvel.vm.CharacterFragmentViewModel
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.view_character_fragment.*
import org.fs.architecture.mvi.common.Failure
import org.fs.architecture.mvi.common.Idle
import org.fs.architecture.mvi.common.Operation
import org.fs.architecture.mvi.core.AbstractFragment
import org.fs.architecture.mvi.util.ObservableList
import javax.inject.Inject

class CharacterFragment: AbstractFragment<CharacterModel, CharacterFragmentViewModel>(), CharacterFragmentView {

    @Inject lateinit var dataSet: ObservableList<Any>
    @Inject lateinit var characterAdapter: CharacterAdapter

    private val args: CharacterFragmentArgs by navArgs()

    override val layoutRes: Int get() = R.layout.view_character_fragment

    private var character: Character = Character.EMPTY

    override fun setUp(state: Bundle?) {
        character = args.character
        viewRecycler.setUp(characterAdapter)

        viewSwipeRefreshLayout.setColorSchemeResources(R.color.purple_200, R.color.purple_700, R.color.purple_500)
        viewSwipeRefreshLayout.isEnabled = false
    }

    override fun attach() {
        super.attach()

        disposeBag += viewModel.state()
                .map { state ->
                    if (state is Operation) {
                        return@map state.type == LOAD
                    }
                    return@map false
                }
                .subscribe(viewSwipeRefreshLayout::bindProgress)

        disposeBag += viewModel.storage()
                .subscribe(::render)

        checkIfInitialLoadNeeded()
    }

    override fun render(model: CharacterModel) = when(model.state) {
        is Idle -> Unit
        is Failure -> showError(model.state.error)
        is Operation -> when(model.state.type) {
            LOAD -> render(model.comics)
            else -> Unit
        }
    }

    private fun render(data: List<Comic>) {
        if (data.isNotEmpty()) {
            dataSet.add(character)
            dataSet.add(data)
        }
    }

    private fun checkIfInitialLoadNeeded() {
        if (dataSet.isEmpty()) {
            val characterId = character.id ?: Long.MIN_VALUE
            accept(LoadCharacterComicsEvent(characterId))
        }
    }
}