package io.fs.marvel.view.holder

import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import io.fs.marvel.R
import io.fs.marvel.common.glide.GlideRequests
import io.fs.marvel.model.data.Comic
import io.fs.marvel.util.setUp
import io.fs.marvel.view.adapter.ComicAdapter
import kotlinx.android.synthetic.main.view_comics_item.view.*
import org.fs.architecture.mvi.util.ObservableList
import org.fs.architecture.mvi.util.inflate

class ComicsCharacterViewHolder(view: View, glide: GlideRequests): BaseCharacterViewHolder(view) {

    private val dataSet by lazy { ObservableList<Comic>() }
    private val comicAdapter by lazy { ComicAdapter(dataSet, glide) }

    private val context by lazy { itemView.context }
    private val resources by lazy { context.resources }

    private val horizontalDrawable by lazy { ResourcesCompat.getDrawable(resources, R.drawable.ic_horizontal_divider, context.theme) }

    private val snapHelper by lazy { LinearSnapHelper() }

    private val viewRecycler by lazy { itemView.viewRecycler }

    constructor(parent: ViewGroup, glide: GlideRequests): this(parent.inflate(R.layout.view_comics_item), glide)

    init {
        viewRecycler.setUp(comicAdapter, RecyclerView.HORIZONTAL, horizontalDrawable)
    }

    override fun bind(value: Any) {
        dataSet.clear() // clear it if it is reused
        // ugly but gotta cast it
        val data = (value as? List<Comic>) ?: emptyList()
        if (data.isNotEmpty()) {
            dataSet.addAll(data)
        }

        snapHelper.attachToRecyclerView(viewRecycler)
    }

    override fun unbind() = Unit
}