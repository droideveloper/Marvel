package io.fs.marvel.common.repo

import io.fs.marvel.model.data.Character
import io.fs.marvel.model.data.Comic
import io.fs.marvel.net.EndpointProxy
import io.fs.marvel.net.Resource
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImp @Inject constructor(private val proxy: EndpointProxy): CharacterRepository {


    override fun characters(offset: Int, limit: Int): Observable<Resource<List<Character>>> = proxy.characters(offset, limit)

    override fun character(characterId: Long): Observable<Resource<List<Character>>> = proxy.character(characterId)

    override fun comics(characterId: Long): Observable<Resource<List<Comic>>> = proxy.comics(characterId)
}