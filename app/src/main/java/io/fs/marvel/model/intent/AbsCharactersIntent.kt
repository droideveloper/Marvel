package io.fs.marvel.model.intent

import io.fs.marvel.common.repo.CharacterRepository
import io.fs.marvel.model.data.Character
import io.fs.marvel.model.state.CharactersModel
import io.fs.marvel.net.Resource
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.fs.architecture.mvi.common.*
import java.io.IOException

abstract class AbsCharactersIntent(private val offset: Int, private val limit: Int, private val charactersRepository: CharacterRepository): ObservableIntent<CharactersModel>() {

    abstract val state: Int

    override fun invoke(): Observable<Reducer<CharactersModel>> = charactersRepository.characters(offset, limit)
            .concatMap(::success)
            .onErrorResumeNext(::failure)
            .startWith(initial())
            .subscribeOn(Schedulers.io())

    private fun success(resource: Resource<List<Character>>): Observable<Reducer<CharactersModel>> = when(resource) {
        is Resource.Success<List<Character>> -> Observable.just(
                { o -> o.copy(state = Operation(state), data = resource.data ?: emptyList()) },
                { o -> o.copy(state = Idle, data = emptyList()) })
        is Resource.Failure<List<Character>> -> Observable.just(
                { o -> o.copy(state = Failure(resource.error ?: IOException("unknown"))) },
                { o -> o.copy(state = Idle) })
    }

    private fun failure(error: Throwable): Observable<Reducer<CharactersModel>> = Observable.just(
            { o -> o.copy(state = Failure(error)) },
            { o -> o.copy(state = Idle) })

    private fun initial(): Reducer<CharactersModel> = { o -> o.copy(state = Operation(state), data = emptyList())}
}