package com.zen.android.rxjava;

import android.support.annotation.NonNull;

import org.junit.Test;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * DeferTest
 * <p>
 *
 * @author yangz
 * @version 2015/11/6
 */
public class DeferTest {

    @Test
    public void testDefer() throws Exception {
        // all in Main
        createObservable()
                .subscribe(integer -> {
                    showLog("subscribe " + integer);
                });
        showLog("end");
        Thread.sleep(5);
    }

    @Test
    public void testDeferWithObserveOn() throws Exception {
        // emit values & doOnNext in Main
        // subscribe in New Thread
        createObservable()
                .observeOn(Schedulers.newThread())
                .subscribe(integer -> {
                    showLog("subscribe " + integer);
                });
        showLog("end");
        Thread.sleep(5);
    }

    @Test
    public void testDeferWithSubscribeOn() throws Exception {
        // all in New Thread
        createObservable()
                .subscribeOn(Schedulers.newThread())
                .subscribe(integer -> {
                    showLog("subscribe " + integer);
                });
        showLog("end");
        Thread.sleep(5);
    }

    @Test
    public void testDeferSchedulers() throws Exception {
        // emit values & doOnNext in IO Thread
        // subscribe in New Thread
        createObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(integer -> {
                    showLog("subscribe " + integer);
                });
        showLog("end");
        Thread.sleep(5);
    }

    @Test
    public void testDeferSync() throws Exception {
        createObservable()
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(integer -> {
                    showLog("subscribe " + integer);
                });
        showLog("end");
        Thread.sleep(10);
    }

    @NonNull
    private Observable<Integer> createObservable() {
        return Observable.defer(() -> {
            showLog("emit values");
            return Observable.just(1, 2, 3);
        })
                .doOnNext(integer -> showLog("doOnNext " + integer));
    }

    private static void showLog(String tag) {
        System.out.println(tag + " in " + Thread.currentThread().getName());
    }
}
