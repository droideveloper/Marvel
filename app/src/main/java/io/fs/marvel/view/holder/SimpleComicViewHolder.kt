package io.fs.marvel.view.holder

import android.view.View
import android.view.ViewGroup
import io.fs.marvel.R
import io.fs.marvel.common.glide.GlideRequests
import io.fs.marvel.model.data.Comic
import io.fs.marvel.model.data.Thumbnail
import io.fs.marvel.util.toDate
import io.fs.marvel.util.toDateString
import kotlinx.android.synthetic.main.view_comic_item.view.*
import org.fs.architecture.mvi.util.inflate
import java.util.*

class SimpleComicViewHolder(view: View, private val glide: GlideRequests): BaseComicViewHolder(view) {

    private val viewComicImage by lazy { itemView.viewComicImage }
    private val viewComicText by lazy { itemView.viewComicText }
    private val viewComicReleaseDate by lazy { itemView.viewComicReleaseDate }

    constructor(parent: ViewGroup, glide: GlideRequests): this(parent.inflate(R.layout.view_comic_item), glide)

    override fun bind(value: Comic) {
        glide.clear(viewComicImage)

        val thumb = value.thumbnail ?: Thumbnail.EMPTY
        if (thumb != Thumbnail.EMPTY) {
            glide.load(thumb.path + "." + thumb.extension)
                .applyCrop()
                .into(viewComicImage)
        }

        val date = value.modified?.toDate() ?: Date()

        viewComicText.text = value.title
        viewComicReleaseDate.text = date.toDateString()
    }

    override fun unbind() = Unit
}