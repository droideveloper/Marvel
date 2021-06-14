package io.fs.marvel.common.di.module

import dagger.Module
import dagger.Provides
import io.fs.marvel.model.data.Character
import org.fs.architecture.mvi.common.ForFragment
import org.fs.architecture.mvi.util.ObservableList

@Module
class ProviderFragmentModule {

    @ForFragment @Provides fun provideCharactersDataSet(): ObservableList<Character> = ObservableList()

    @ForFragment @Provides fun provideCharacterDataSet(): ObservableList<Any> = ObservableList()
}