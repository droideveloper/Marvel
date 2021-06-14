package io.fs.marvel.common.net

import android.net.Uri
import io.fs.marvel.BuildConfig
import io.fs.marvel.util.C.Endpoints.Companion.API_KEY
import io.fs.marvel.util.C.Endpoints.Companion.HASH_KEY
import io.fs.marvel.util.C.Endpoints.Companion.TS_KEY
import io.fs.marvel.util.md5
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorizationInterceptor @Inject constructor(): Interceptor {

  private val calendar by lazy { Calendar.getInstance(TimeZone.getDefault()) }

  override fun intercept(chain: Interceptor.Chain): Response {
    val req = chain.request()

    val uriString = req.url().toString()
    val timestamp = calendar.timeInMillis

    val hash = ("$timestamp" + BuildConfig.P_API_KEY + BuildConfig.API_KEY).md5()


    val uri = Uri.parse(uriString)
            .buildUpon()
            .appendQueryParameter(API_KEY, BuildConfig.API_KEY)
            .appendQueryParameter(TS_KEY, "$timestamp")
            .appendQueryParameter(HASH_KEY, hash)

    val newRequest = req.newBuilder()
            .url(uri.toString())

    // build new request
    val newReq = newRequest.build()
    // we reached where we do not have those
    return chain.proceed(newReq)
  }
}