package io.fs.marvel.view

import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import io.fs.marvel.R
import io.fs.marvel.model.data.Character
import io.fs.marvel.model.event.LoadCharactersEvent
import io.fs.marvel.model.event.LoadMoreCharactersEvent
import io.fs.marvel.model.event.SelectCharacterEvent
import io.fs.marvel.model.state.CharactersModel
import io.fs.marvel.util.Operations.Companion.LOAD
import io.fs.marvel.util.Operations.Companion.LOAD_MORE
import io.fs.marvel.util.bindProgress
import io.fs.marvel.util.setUp
import io.fs.marvel.util.showError
import io.fs.marvel.view.adapter.CharactersAdapter
import io.fs.marvel.vm.CharactersFragmentViewModel
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.view_characters_fragment.*
import org.fs.architecture.mvi.common.BusManager
import org.fs.architecture.mvi.common.Failure
import org.fs.architecture.mvi.common.Idle
import org.fs.architecture.mvi.common.Operation
import org.fs.architecture.mvi.core.AbstractFragment
import org.fs.architecture.mvi.util.EMPTY
import org.fs.architecture.mvi.util.ObservableList
import org.fs.rx.extensions.v4.util.refreshes
import org.fs.rx.extensions.v7.util.loadMore
import javax.inject.Inject

class CharactersFragment: AbstractFragment<CharactersModel, CharactersFragmentViewModel>(), CharactersFragmentView {

    @Inject lateinit var dataSet: ObservableList<Character>
    @Inject lateinit var charactersAdapter: CharactersAdapter

    override val layoutRes: Int get() = R.layout.view_characters_fragment

    private val verticalDivider by lazy { ResourcesCompat.getDrawable(resources, R.drawable.ic_vertical_divider, context?.theme) }

    override fun setUp(state: Bundle?) {
        viewRecycler.setUp(charactersAdapter, divider = verticalDivider)
        viewSwipeRefreshLayout.setColorSchemeResources(R.color.purple_200, R.color.purple_700, R.color.purple_500)
    }

    override fun attach() {
        super.attach()

        disposeBag += BusManager.add { evt -> when(evt) {
                is SelectCharacterEvent -> {
                    val direction = CharactersFragmentDirections.actionCharacterDetail(evt.character, evt.character.name ?: String.EMPTY)
                    view?.let { v ->
                        val controller = v.findNavController()
                        controller.navigate(direction)
                    }
                }
                else -> Unit
            }
        }

        disposeBag += viewSwipeRefreshLayout.refreshes()
            .doOnNext { dataSet.clear() }
            .map { LoadCharactersEvent() }
            .subscribe(::accept)

        disposeBag += viewRecycler.loadMore()
            .filter { dataSet.isNotEmpty() }
            .map { LoadMoreCharactersEvent(offset = dataSet.size) }
            .subscribe(::accept)

        disposeBag += viewModel.state()
            .map { state ->
                if (state is Operation) {
                    return@map state.type == LOAD
                }
                return@map false
            }
            .subscribe(viewSwipeRefreshLayout::bindProgress)

        disposeBag += viewModel.state()
            .map { state ->
                if (state is Operation) {
                    return@map state.type == LOAD_MORE
                }
                return@map false
            }
            .subscribe(::loadMore)

        disposeBag += viewModel.storage()
                .subscribe(::render)

        checkIfInitialLoadNeeded()
    }

    override fun render(model: CharactersModel) = when(model.state) {
        is Idle -> Unit
        is Failure -> showError(model.state.error)
        is Operation -> when(model.state.type) {
            LOAD, LOAD_MORE -> render(model.data)
            else -> Unit
        }
    }

    private fun render(data: List<Character>) {
        if (data.isNotEmpty()) {
            dataSet.addAll(data)
        }
    }

    private fun checkIfInitialLoadNeeded() {
        if (dataSet.isEmpty()) {
            accept(LoadCharactersEvent())
        }
    }

    private fun loadMore(loadMore: Boolean) {
        val position = dataSet.indexOfFirst { c -> c == Character.EMPTY }
        if (position == -1) {
            if (loadMore) {
                dataSet.add(Character.EMPTY)
            }
        } else {
            if (!loadMore) {
                dataSet.removeAt(position)
            }
        }
    }
}