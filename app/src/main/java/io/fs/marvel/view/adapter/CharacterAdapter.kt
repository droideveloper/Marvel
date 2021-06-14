package io.fs.marvel.view.adapter

import android.content.Context
import android.view.ViewGroup
import io.fs.marvel.common.glide.GlideApp
import io.fs.marvel.model.data.Character
import io.fs.marvel.view.holder.BaseCharacterViewHolder
import io.fs.marvel.view.holder.ComicsCharacterViewHolder
import io.fs.marvel.view.holder.SpotCharacterViewHolder
import org.fs.architecture.mvi.common.ForFragment
import org.fs.architecture.mvi.core.AbstractRecyclerViewAdapter
import org.fs.architecture.mvi.util.ObservableList
import java.lang.IllegalArgumentException
import javax.inject.Inject

@ForFragment
class CharacterAdapter @Inject constructor(dataSet: ObservableList<Any>, context: Context): AbstractRecyclerViewAdapter<Any, BaseCharacterViewHolder>(dataSet) {

    companion object {
        private const val TYPE_SPOT = 0x01
        private const val TYPE_COMICS = 0x02
    }

    private val glide by lazy { GlideApp.with(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCharacterViewHolder = when(viewType) {
        TYPE_SPOT -> SpotCharacterViewHolder(parent, glide)
        TYPE_COMICS -> ComicsCharacterViewHolder(parent, glide)
        else -> throw IllegalArgumentException("could not resolve viewType for $viewType")
    }

    override fun getItemViewType(position: Int): Int {
        val item = dataSet[position]
        return when {
            item is Character -> TYPE_SPOT
            else -> TYPE_COMICS
        }
    }
}