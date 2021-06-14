package io.fs.marvel

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.fs.marvel.common.di.DaggerAppComponent

class App: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>  = DaggerAppComponent.builder()
        .create(this)
}