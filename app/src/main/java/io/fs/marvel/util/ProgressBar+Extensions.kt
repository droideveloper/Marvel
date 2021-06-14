package io.fs.marvel.util

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.bindProgress(showProgress: Boolean) {
    visibility = when {
        showProgress -> View.VISIBLE
        else -> View.INVISIBLE
    }
    isIndeterminate = showProgress
}