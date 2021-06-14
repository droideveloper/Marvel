package io.fs.marvel.net

import io.fs.marvel.model.data.Character
import io.fs.marvel.model.data.Comic
import io.reactivex.Observable

interface EndpointProxy {

    fun characters(offset: Int, limit: Int): Observable<Resource<List<Character>>>

    fun character(characterId: Long): Observable<Resource<List<Character>>>
    fun comics(characterId: Long): Observable<Resource<List<Comic>>>
}