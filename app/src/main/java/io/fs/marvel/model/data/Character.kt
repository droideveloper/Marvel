package io.fs.marvel.model.data

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Character(
        val id: Long? = null,
        val name: String? = null,
        val description: String? = null,
        val thumbnail: Thumbnail? = null
): Parcelable {

    companion object {
        val EMPTY = Character()
    }
}