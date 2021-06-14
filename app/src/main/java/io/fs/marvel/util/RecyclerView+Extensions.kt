package io.fs.marvel.util

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.fs.marvel.util.C.Companion.ITEM_CACHE_SIZE

fun RecyclerView.setUp(dataAdapter: RecyclerView.Adapter<*>, orientation: Int = RecyclerView.VERTICAL, divider: Drawable? = null) {
    setItemViewCacheSize(ITEM_CACHE_SIZE)
    layoutManager = LinearLayoutManager(context, orientation, false)
    adapter = dataAdapter
    divider?.let { drawable ->
        val ori = when {
            orientation == RecyclerView.VERTICAL -> DividerItemDecoration.VERTICAL
            else -> DividerItemDecoration.HORIZONTAL
        }
        val decoration = DividerItemDecoration(context, ori)
        decoration.setDrawable(drawable)
        addItemDecoration(decoration)
    }
}