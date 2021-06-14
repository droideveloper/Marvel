package io.fs.marvel.view.holder

import android.view.View
import io.fs.marvel.model.data.Comic
import org.fs.architecture.mvi.core.AbstractRecyclerViewHolder

abstract class BaseComicViewHolder(view: View): AbstractRecyclerViewHolder<Comic>(view)