package io.fs.marvel.model.data

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Comic(
        val id: Long? = null,
        val digitalId: Long? = null,
        val title: String? = null,
        val issueNumber: Int? = null,
        val description: String? = null,
        val modified: String? = null,
        val isbn: String? = null,
        val upc: String? = null,
        val diamondCode: String? = null,
        val ean: String? = null,
        val issn: String? = null,
        val format: String? = null,
        val pageCount: Int? = null,
        val thumbnail: Thumbnail? = null
): Parcelable {

    companion object {
        val EMPTY = Comic()
    }
}
