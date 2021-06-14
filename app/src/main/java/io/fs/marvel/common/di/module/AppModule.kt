package io.fs.marvel.common.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.fs.marvel.App
import io.fs.marvel.common.net.AuthorizationInterceptor
import io.fs.marvel.common.repo.CharacterRepository
import io.fs.marvel.common.repo.CharacterRepositoryImp
import io.fs.marvel.net.EndpointProxy
import io.fs.marvel.net.EndpointProxyImp
import io.fs.marvel.view.MainActivity
import okhttp3.Interceptor
import org.fs.architecture.mvi.common.ForActivity
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Singleton @Binds abstract fun bindApplication(app: App): Application
    @Singleton @Binds abstract fun bindContext(app: Application): Context

    @Singleton @Binds abstract fun bindEndpointProxy(proxy: EndpointProxyImp): EndpointProxy

    @Singleton @Binds abstract fun bindCharacterRepository(repo: CharacterRepositoryImp): CharacterRepository

    @Singleton @Binds abstract fun bindAuthorizationInterceptor(inter: AuthorizationInterceptor): Interceptor

    @ForActivity @ContributesAndroidInjector(modules = [ActivityModule::class, ProviderActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity
}