package io.fs.marvel.common.di.module

import dagger.Binds
import dagger.Module
import io.fs.marvel.view.CharacterFragment
import io.fs.marvel.view.CharacterFragmentView
import io.fs.marvel.view.CharactersFragment
import io.fs.marvel.view.CharactersFragmentView
import org.fs.architecture.mvi.common.ForFragment

@Module
abstract class FragmentModule {

    @ForFragment @Binds abstract fun bindCharactersFragmentView(fragment: CharactersFragment): CharactersFragmentView
    @ForFragment @Binds abstract fun bindCharacterFragmentView(fragment: CharacterFragment): CharacterFragmentView
}