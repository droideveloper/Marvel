package io.fs.marvel.view.holder

import android.view.View
import io.fs.marvel.model.data.Character
import org.fs.architecture.mvi.core.AbstractRecyclerViewHolder

abstract class BaseCharactersViewHolder(view: View): AbstractRecyclerViewHolder<Character>(view)