package io.fs.marvel.common.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import io.fs.marvel.App
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Singleton @Binds abstract fun bindApplication(app: App): Application
    @Singleton @Binds abstract fun bindContext(app: Application): Context
}