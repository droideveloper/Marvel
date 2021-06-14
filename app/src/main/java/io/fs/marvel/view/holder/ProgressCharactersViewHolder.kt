package io.fs.marvel.view.holder

import android.view.View
import android.view.ViewGroup
import io.fs.marvel.R
import io.fs.marvel.model.data.Character
import io.fs.marvel.util.bindProgress
import kotlinx.android.synthetic.main.view_progress_item.view.*
import org.fs.architecture.mvi.util.inflate

class ProgressCharactersViewHolder(view: View): BaseCharactersViewHolder(view) {

    private val viewProgressBar by lazy { itemView.viewProgressBar }

    constructor(parent: ViewGroup): this(parent.inflate(R.layout.view_progress_item))

    override fun bind(value: Character) = viewProgressBar.bindProgress(true)

    override fun unbind() = viewProgressBar.bindProgress(false)
}