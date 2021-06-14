package io.fs.marvel.common.glide

import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.util.ContentLengthInputStream
import okhttp3.*
import java.io.IOException
import java.io.InputStream

class OkHttpStreamFetcher constructor(private val factory: Call.Factory, private val url: GlideUrl): DataFetcher<InputStream>, Callback {

  private var stream: InputStream? = null
  private var body: ResponseBody? = null
  private var callback: DataFetcher.DataCallback<in InputStream>? = null
  @Volatile private var call: Call? = null

  override fun getDataClass(): Class<InputStream> = InputStream::class.java

  override fun cleanup() {
    stream?.close()
    stream = null
    body?.close()
    body = null
    call?.cancel()
    call = null
    callback = null
  }

  override fun getDataSource(): DataSource = DataSource.REMOTE

  override fun cancel() = call?.cancel() ?: Unit

  override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in InputStream>) {
    val builder = Request.Builder().url(url.toStringUrl())
    url.headers.entries.forEach { e ->
      builder.addHeader(e.key, e.value)
    }
    val request = builder.build()
    this.callback = callback

    call = factory.newCall(request)
    call?.enqueue(this)
  }

  override fun onFailure(call: Call, e: IOException) = callback?.onLoadFailed(e) ?: Unit

  override fun onResponse(call: Call, response: Response) {
    body = response.body()
    if (response.isSuccessful) {
      try {
        body?.let { res ->
          val length = res.contentLength()
          stream = ContentLengthInputStream.obtain(res.byteStream(), length)
          stream?.let { input ->
            callback?.onDataReady(input)
          }
          Unit
        }
      } catch (error: Throwable) {
        onFailure(call, IOException(error))
      }
    } else {
      onFailure(call, IOException(response.message()))
    }
  }
}