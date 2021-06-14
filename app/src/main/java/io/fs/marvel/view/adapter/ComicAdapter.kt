package io.fs.marvel.view.adapter

import android.view.ViewGroup
import io.fs.marvel.common.glide.GlideRequests
import io.fs.marvel.model.data.Comic
import io.fs.marvel.view.holder.BaseComicViewHolder
import io.fs.marvel.view.holder.SimpleComicViewHolder
import org.fs.architecture.mvi.core.AbstractRecyclerViewAdapter
import org.fs.architecture.mvi.util.ObservableList

class ComicAdapter constructor(dataSet: ObservableList<Comic>, private val glide: GlideRequests): AbstractRecyclerViewAdapter<Comic, BaseComicViewHolder>(dataSet) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseComicViewHolder = SimpleComicViewHolder(parent, glide)
}