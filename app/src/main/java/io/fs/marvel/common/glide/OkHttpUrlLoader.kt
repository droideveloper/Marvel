package io.fs.marvel.common.glide

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import okhttp3.Call
import java.io.InputStream

class OkHttpUrlLoader constructor(private val factory: Call.Factory): ModelLoader<GlideUrl, InputStream> {

  override fun buildLoadData(model: GlideUrl, width: Int, height: Int, options: Options): ModelLoader.LoadData<InputStream> = ModelLoader.LoadData(model, OkHttpStreamFetcher(factory, model))

  override fun handles(model: GlideUrl): Boolean = true

  class Factory(private val factory: Call.Factory): ModelLoaderFactory<GlideUrl, InputStream> {

    override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<GlideUrl, InputStream> = OkHttpUrlLoader(factory)

    override fun teardown() = Unit
  }
}