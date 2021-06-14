package io.fs.marvel.util

import android.app.Activity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import org.fs.architecture.mvi.common.View

typealias OSView = android.view.View

fun View.showError(error: Throwable) {
    var view: OSView? = null
    if (this is Activity) {
        view = findViewById(android.R.id.content)
    } else if (this is Fragment) {
        view = this.view
    }
    view?.let { v ->
        Snackbar.make(view, error.localizedMessage ?: "Unknown Error", Snackbar.LENGTH_LONG)
            .show()
    }
}