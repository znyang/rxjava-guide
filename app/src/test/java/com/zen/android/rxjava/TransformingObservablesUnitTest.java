package com.zen.android.rxjava;

import android.support.annotation.NonNull;

import com.zen.android.rxjava.base.Logger;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

/**
 * TransformingObservablesUnitTest
 * <p>
 *
 * @author yangz
 * @version 2015/10/23
 */
public class TransformingObservablesUnitTest {

    @Test
    public void testBuffer() throws Exception {
        Observable.just("1", "2", "3", "4", "5")
                .buffer(2)
                .subscribe(items -> {
                    Logger.log("buffer");
                    for (String item : items) {
                        Logger.log(item);
                    }
                });
    }

    @Test
    public void testFlatMap() throws Exception {
        Observable<String> obs = Observable.range(1, 3)
                .flatMap(this::getMapObservable);

        obs.subscribe(Logger::print);
        Thread.sleep(500);
    }

    @Test
    public void testConcatMap() throws Exception {
        Observable<String> obs = Observable.range(1, 3)
                .concatMap(this::getMapObservable);

        obs.subscribe(Logger::print);
        Thread.sleep(500);
    }

    @Test
    public void testSwitchMap() throws Exception {
        Observable<String> obs = Observable.range(1, 3)
                .switchMap(this::getMapObservable);

        obs.subscribe(Logger::print);
        Thread.sleep(500);
    }

    @NonNull
    private Observable<? extends String> getMapObservable(Integer integer) {
        List<String> data = new ArrayList<>();
        for (int i = 2; --i >= 0; ) {
            data.add(String.valueOf(integer));
        }
        return Observable.from(data).delay(100, TimeUnit.MILLISECONDS);
    }

    @Test
    public void testGroupBy() throws Exception {
        Observable.range(1, 10)
                .groupBy(integer -> integer & 1)
                .subscribe(result -> {
                    result.subscribe(integer -> {
                        Logger.log(result.getKey() + " - " + integer);
                    });
                });
    }



}
