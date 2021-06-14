package io.fs.marvel.net

import io.fs.marvel.model.data.Character
import io.fs.marvel.model.data.Comic
import io.fs.marvel.net.model.OffsetData
import io.fs.marvel.net.model.Response
import retrofit2.http.GET
import io.fs.marvel.util.C.Endpoints
import io.reactivex.Observable
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {

    @GET(Endpoints.CHARACTERS) fun characters(@Query(Endpoints.OFFSET) offset: Int, @Query(Endpoints.LIMIT) limit: Int): Observable<Response<OffsetData<List<Character>>>>
    @GET(Endpoints.CHARACTER_DETAIL) fun character(@Path(Endpoints.CHARACTER_ID) characterId: Long): Observable<Response<OffsetData<List<Character>>>>
    @GET(Endpoints.CHARACTER_COMICS) fun comics(@Path(Endpoints.CHARACTER_ID) characterId: Long): Observable<Response<OffsetData<List<Comic>>>>
}