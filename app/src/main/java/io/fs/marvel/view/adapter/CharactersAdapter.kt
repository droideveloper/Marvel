package io.fs.marvel.view.adapter

import android.content.Context
import android.view.ViewGroup
import io.fs.marvel.common.glide.GlideApp
import io.fs.marvel.model.data.Character
import io.fs.marvel.view.holder.BaseCharactersViewHolder
import io.fs.marvel.view.holder.ProgressCharactersViewHolder
import io.fs.marvel.view.holder.SimpleCharactersViewHolder
import org.fs.architecture.mvi.common.ForFragment
import org.fs.architecture.mvi.core.AbstractRecyclerViewAdapter
import org.fs.architecture.mvi.util.ObservableList
import java.lang.IllegalArgumentException
import javax.inject.Inject

@ForFragment
class CharactersAdapter @Inject constructor(dataSet: ObservableList<Character>, context: Context): AbstractRecyclerViewAdapter<Character, BaseCharactersViewHolder>(dataSet) {

    companion object {
        private const val TYPE_SIMPLE = 0x01
        private const val TYPE_PROGRESS = 0x02
    }

    private val glide by lazy { GlideApp.with(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCharactersViewHolder = when(viewType) {
        TYPE_SIMPLE -> SimpleCharactersViewHolder(parent, glide)
        TYPE_PROGRESS -> ProgressCharactersViewHolder(parent)
        else -> throw IllegalArgumentException("could not resolve viewType for $viewType")
    }

    override fun getItemViewType(position: Int): Int {
        val item = dataSet[position]
        return when {
            item == Character.EMPTY -> TYPE_PROGRESS
            else -> TYPE_SIMPLE
        }
    }
}