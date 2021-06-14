package io.fs.marvel.common.repo

import io.fs.marvel.model.data.Character
import io.fs.marvel.model.data.Comic
import io.fs.marvel.net.Resource
import io.reactivex.Observable

interface CharacterRepository {

    fun characters(offset: Int, limit: Int): Observable<Resource<List<Character>>>

    fun character(characterId: Long): Observable<Resource<List<Character>>>
    fun comics(characterId: Long): Observable<Resource<List<Comic>>>
}