package io.fs.marvel.common.glide

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import io.fs.marvel.BuildConfig
import io.fs.marvel.common.net.AuthorizationInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.InputStream
import kotlin.math.roundToLong

@GlideModule class MarvelGlideModule: AppGlideModule() {

  private val logger by lazy {
    val l = HttpLoggingInterceptor.Logger { msg ->
      if (BuildConfig.DEBUG) {
        Log.println(Log.INFO, MarvelGlideModule::class.java.simpleName, msg)
      }
    }

    HttpLoggingInterceptor(l).apply {
      level = HttpLoggingInterceptor.Level.HEADERS
    }
  }

  private val factory by lazy {
    val builder = OkHttpClient.Builder()
    // add logger if debug
    if (BuildConfig.DEBUG) {
      builder.addInterceptor(logger)
      //builder.addInterceptor(AuthorizationInterceptor())
    }
    // return http
    builder.build()
  }

  override fun applyOptions(context: Context, builder: GlideBuilder) {
    val calculator = MemorySizeCalculator.Builder(context)
      .setBitmapPoolScreens(3f)
      .build()

    val memoryCacheSize = (calculator.memoryCacheSize * 0.6f).roundToLong()
    val diskCacheSize = 1024 * 1024 * 128L // cache 128 mb in disk
    // we set up glide this way
    builder.setMemoryCache(LruResourceCache(memoryCacheSize))
    builder.setBitmapPool(LruBitmapPool(calculator.bitmapPoolSize.toLong()))
    builder.setDiskCache(DiskLruCacheFactory(Glide.getPhotoCacheDir(context)?.absolutePath, diskCacheSize))
  }

  override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
    registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(factory))
  }

  override fun isManifestParsingEnabled(): Boolean = false
}