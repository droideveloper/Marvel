package io.fs.marvel.net

import io.fs.marvel.model.data.Character
import io.fs.marvel.model.data.Comic
import io.fs.marvel.util.toResource
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EndpointProxyImp @Inject constructor(private val endpoint: Endpoint): EndpointProxy {

    override fun characters(offset: Int, limit: Int): Observable<Resource<List<Character>>> = endpoint.characters(offset, limit)
            .toResource()

    override fun character(characterId: Long): Observable<Resource<List<Character>>> = endpoint.character(characterId)
            .toResource()

    override fun comics(characterId: Long): Observable<Resource<List<Comic>>> = endpoint.comics(characterId)
            .toResource()
}