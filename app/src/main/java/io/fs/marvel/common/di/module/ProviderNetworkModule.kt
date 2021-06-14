package io.fs.marvel.common.di.module

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.fs.marvel.BuildConfig
import io.fs.marvel.net.Endpoint
import io.fs.marvel.net.factory.RxJava2CallAdapterFactory
import io.fs.marvel.util.log
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.lang.IllegalArgumentException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ProviderNetworkModule {

    private companion object {
        private const val CACHE_DIR = "cache"
        private const val CACHE_SIZE = 12 * 1024 * 1024L
        private const val DEFAULT_TIMEOUT = 20L

        private const val MEDIA_TYPE = "application/json; charset=UTF-8"
    }

    @Singleton  @Provides fun provideHttpUrl(): HttpUrl {
        val url = HttpUrl.parse(BuildConfig.BASE_URL)
        if (url != null) {
            return url
        }
        throw IllegalArgumentException("we can not parse url string ${BuildConfig.BASE_URL}")
    }

    @Singleton @Provides fun provideHttpFactory(context: Context, authInterceptor: Interceptor): Call.Factory {
        val file = File(context.cacheDir, CACHE_DIR)
        val cache = Cache(file, CACHE_SIZE)

        val builder = OkHttpClient.Builder()
                .cache(cache)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(authInterceptor)

        if (BuildConfig.DEBUG) {
            val l = HttpLoggingInterceptor.Logger { msg -> log(msg) }
            val logger = HttpLoggingInterceptor(l)
            logger.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logger)
        }

        return builder.build()
    }

    @Singleton @Provides fun provideMediaType(): MediaType {
        val mediaType = MediaType.parse(MEDIA_TYPE)
        if (mediaType != null) {
            return mediaType
        }
        throw IllegalArgumentException("we can not parse media type string $MEDIA_TYPE")
    }

    @Singleton @Provides fun provideMoshiConverterFactory(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @Singleton @Provides fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton @Provides fun provideRetrofit(factory: Call.Factory, httpUrl: HttpUrl, converterFactory: Converter.Factory, moshi: Moshi): Retrofit = Retrofit.Builder()
            .baseUrl(httpUrl)
            .callFactory(factory)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create(moshi))
            .build()

    @Singleton @Provides fun provideEndpointLayer(retrofit: Retrofit): Endpoint = retrofit.create(Endpoint::class.java)
}