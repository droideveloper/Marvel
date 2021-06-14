package io.fs.marvel.view.holder

import android.view.View
import android.view.ViewGroup
import io.fs.marvel.R
import io.fs.marvel.common.glide.GlideRequests
import io.fs.marvel.model.data.Character
import io.fs.marvel.model.data.Thumbnail
import kotlinx.android.synthetic.main.view_character_spot_item.view.*
import org.fs.architecture.mvi.util.inflate

class SpotCharacterViewHolder(view: View, private val glide: GlideRequests): BaseCharacterViewHolder(view) {

    private val viewCharacterImage by lazy { itemView.viewCharacterImage }
    private val viewCharacterText by lazy { itemView.viewCharacterText }
    private val viewCharacterDescription by lazy { itemView.viewCharacterDescription }

    constructor(parent: ViewGroup, glide: GlideRequests): this(parent.inflate(R.layout.view_character_spot_item), glide)

    override fun bind(value: Any) {
        glide.clear(viewCharacterImage)
        if (value is Character) {
           val thumb = value.thumbnail ?: Thumbnail.EMPTY
           if (thumb != Thumbnail.EMPTY) {
               glide.load(thumb.path + "." + thumb.extension)
                       .applyCrop()
                       .into(viewCharacterImage)
           }

            viewCharacterText.text = value.name
            viewCharacterDescription.text = value.description
        }
    }

    override fun unbind() = Unit
}