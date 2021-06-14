package io.fs.marvel.model.data

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Thumbnail(
        val path: String? = null,
        val extension: String? = null): Parcelable {

    companion object {
        val EMPTY = Thumbnail()
    }
}