package io.fs.marvel.net.factory;

import com.squareup.moshi.JsonAdapter;
import io.fs.marvel.net.factory.moshi.MoshiResponseBodyConverter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Converter;
import retrofit2.Response;

final class BodyObservable<T> extends Observable<T> {

  private final Observable<Response<T>> upstream;
  private final JsonAdapter<T> adapter;

  BodyObservable(Observable<Response<T>> upstream, JsonAdapter<T> adapter) {
    this.upstream = upstream;
    this.adapter = adapter;
  }

  @Override protected void subscribeActual(Observer<? super T> observer) {
    upstream.subscribe(new BodyObserver<>(observer, new MoshiResponseBodyConverter<>(adapter)));
  }

  private static class BodyObserver<R> implements Observer<Response<R>> {

    private final Observer<? super R> observer;
    private final Converter<ResponseBody, R> converter;

    BodyObserver(Observer<? super R> observer, Converter<ResponseBody, R> converter) {
      this.observer = observer;
      this.converter = converter;
    }

    @Override public void onSubscribe(@NotNull Disposable disposable) {
      observer.onSubscribe(disposable);
    }

    @Override public void onNext(Response<R> response) {
      if (response.isSuccessful()) {
        observer.onNext(response.body());
      } else {
        try {
          final ResponseBody body = response.errorBody();
          if (body != null) {
            final R errorResponse = converter.convert(body);
            observer.onNext(errorResponse);
          } else {
            throw new IllegalArgumentException("no body exist for error or success");
          }
        } catch (Throwable error) {
          try {
            observer.onError(error);
          } catch (Throwable inner) {
            Exceptions.throwIfFatal(inner);
            RxJavaPlugins.onError(new CompositeException(error, inner));
          }
        }
      }
    }

    @Override public void onComplete() {
      observer.onComplete();
    }

    @Override public void onError(@NotNull Throwable throwable) {
      observer.onError(throwable);
    }
  }
}