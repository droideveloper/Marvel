package io.fs.marvel.model.intent

import io.fs.marvel.common.repo.CharacterRepository
import io.fs.marvel.model.data.Comic
import io.fs.marvel.model.state.CharacterModel
import io.fs.marvel.net.Resource
import io.fs.marvel.util.Operations.Companion.LOAD
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.fs.architecture.mvi.common.*
import java.io.IOException

class LoadCharacterComicsIntent(
        private val characterId: Long,
        private val characterRepository: CharacterRepository): ObservableIntent<CharacterModel>() {


    override fun invoke(): Observable<Reducer<CharacterModel>> = characterRepository.comics(characterId)
            .concatMap(::success)
            .onErrorResumeNext(::failure)
            .startWith(initial())
            .subscribeOn(Schedulers.io())

    private fun success(resource: Resource<List<Comic>>): Observable<Reducer<CharacterModel>> = when(resource) {
        is Resource.Success<List<Comic>> -> Observable.just(
                { o -> o.copy(state = Operation(LOAD), comics = resource.data ?: emptyList())},
                { o -> o.copy(state = Idle, comics = emptyList())})
        is Resource.Failure<List<Comic>> -> Observable.just(
                { o -> o.copy(state = Failure(resource.error ?: IOException("uknown error"))) },
                { o -> o.copy(state = Idle)})
    }

    private fun failure(error: Throwable): Observable<Reducer<CharacterModel>> = Observable.just(
            { o -> o.copy(state = Failure(error)) },
            { o -> o.copy(state = Idle) })

    private fun initial(): Reducer<CharacterModel> = { o -> o.copy(state = Operation(LOAD), comics = emptyList()) }
}