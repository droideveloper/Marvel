package io.fs.marvel.util

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

fun SwipeRefreshLayout.bindProgress(showProgress: Boolean) {
    isRefreshing = showProgress
}