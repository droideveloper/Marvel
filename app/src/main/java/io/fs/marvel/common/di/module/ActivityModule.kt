package io.fs.marvel.common.di.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.fs.marvel.view.CharacterFragment
import io.fs.marvel.view.CharactersFragment
import io.fs.marvel.view.MainActivity
import io.fs.marvel.view.MainActivityView
import org.fs.architecture.mvi.common.ForActivity
import org.fs.architecture.mvi.common.ForFragment

@Module
abstract class ActivityModule {

    @ForActivity @Binds abstract fun bindMainActivityView(activity: MainActivity): MainActivityView

    @ForFragment @ContributesAndroidInjector(modules = [FragmentModule::class, ProviderFragmentModule::class])
    abstract fun contributeCharactersFragment(): CharactersFragment

    @ForFragment @ContributesAndroidInjector(modules = [FragmentModule::class, ProviderFragmentModule::class])
    abstract fun contributeCharacterFragment(): CharacterFragment
}