package io.fs.marvel.common.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.fs.marvel.App
import io.fs.marvel.common.di.module.AppModule
import io.fs.marvel.common.di.module.ProviderAppModule
import io.fs.marvel.common.di.module.ProviderNetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ProviderAppModule::class,
    ProviderNetworkModule::class
])
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<App>()
}