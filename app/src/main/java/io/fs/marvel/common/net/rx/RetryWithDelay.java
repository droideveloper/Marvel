package io.fs.marvel.common.net.rx;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.concurrent.TimeUnit;

public class RetryWithDelay implements Function<Observable<? extends Throwable>, Observable<?>> {

  private final int max;
  private final long delay;
  private final TimeUnit unit;

  private int retryCount;

  public RetryWithDelay(final int max, final long delay, final TimeUnit unit) {
    this.max = max;
    this.delay = delay;
    this.unit = unit;
    this.retryCount = 0;
  }

  @Override public Observable<?> apply(final Observable<? extends Throwable> attempts) {
    return attempts
      .flatMap((Function<Throwable, Observable<?>>) throwable -> {
        if (++retryCount < max) {
          // When this Observable calls onNext, the original
          // Observable will be retried (i.e. re-subscribed).
          return Observable.timer(delay, unit);
        }

        // Max retries hit. Just pass the error along.
        return Observable.error(throwable);
      });
  }
}
