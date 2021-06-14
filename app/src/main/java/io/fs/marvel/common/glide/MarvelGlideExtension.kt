package io.fs.marvel.common.glide

import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.request.BaseRequestOptions
import io.fs.marvel.R
import io.fs.marvel.util.applyBase

@GlideExtension
sealed class MarvelGlideExtension {

  companion object {

    @JvmStatic @GlideOption fun applyCenterInside(options: BaseRequestOptions<*>): BaseRequestOptions<*> = options.centerInside()
      .applyBase()

    @JvmStatic @GlideOption fun applyCircularCrop(options: BaseRequestOptions<*>): BaseRequestOptions<*> = options.circleCrop()
      .applyBase(R.drawable.ic_placeholder_circular, R.drawable.ic_error_circular_placeholder)

    @JvmStatic @GlideOption fun applyCrop(options: BaseRequestOptions<*>): BaseRequestOptions<*> = options.centerCrop()
      .applyBase()
  }
}