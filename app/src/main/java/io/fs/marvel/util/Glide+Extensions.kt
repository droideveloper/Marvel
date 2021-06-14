package io.fs.marvel.util

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.BaseRequestOptions
import io.fs.marvel.R

// glide options base
fun BaseRequestOptions<*>.applyBase(placeHolder: Int = R.drawable.ic_placeholder, errorPlaceHolder: Int = R.drawable.ic_error_placeholder): BaseRequestOptions<*> = placeholder(placeHolder)
  .error(errorPlaceHolder)
  .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
  .dontAnimate()