package io.fs.marvel.view.holder

import android.view.View
import android.view.ViewGroup
import io.fs.marvel.R
import io.fs.marvel.common.glide.GlideRequests
import io.fs.marvel.model.data.Character
import io.fs.marvel.model.data.Thumbnail
import io.fs.marvel.model.event.SelectCharacterEvent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.view_character_item.view.*
import org.fs.architecture.mvi.common.BusManager
import org.fs.architecture.mvi.util.inflate
import org.fs.rx.extensions.util.clicks

class SimpleCharactersViewHolder(view: View, private val glide: GlideRequests): BaseCharactersViewHolder(view) {

    private val disposeBag by lazy { CompositeDisposable() }

    private val viewCharacterImage by lazy { itemView.viewCharacterImage }
    private val viewCharacterText by lazy { itemView.viewCharacterText }

    constructor(parent: ViewGroup, glide: GlideRequests): this(parent.inflate(R.layout.view_character_item), glide)

    override fun bind(value: Character) {
        glide.clear(viewCharacterImage) // clear it first

        viewCharacterText.text = value.name

        val thumb = value.thumbnail ?: Thumbnail.EMPTY
        if (thumb != Thumbnail.EMPTY) {
            glide.load(thumb.path + "." + thumb.extension)
                .applyCircularCrop()
                .into(viewCharacterImage)
        }

        disposeBag += bindSelectCharacterEvent(value).subscribe(BusManager.Companion::send)
    }

    override fun unbind() = disposeBag.clear()

    private fun bindSelectCharacterEvent(value: Character): Observable<SelectCharacterEvent> = itemView.clicks()
        .map { SelectCharacterEvent(value) }
}