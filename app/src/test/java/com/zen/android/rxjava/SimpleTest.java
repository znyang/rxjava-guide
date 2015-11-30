package com.zen.android.rxjava;

import org.junit.Test;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * SimpleTest
 *
 * @author zeny
 * @version 2015.11.30
 */
public class SimpleTest {

    private int buildValue() {
        System.out.println("build");
        return 1;
    }

    @Test
    public void testJust() throws Exception {
        Observable obs = Observable.just(buildValue());
        System.out.println("create");
        obs.subscribe();
        System.out.println("end");
    }

    @Test
    public void testDefer() throws Exception {
        Observable<Integer> obs = Observable.defer(() -> {
            System.out.println("start");
            return Observable.just(buildValue());
        });
        System.out.println("create");
        obs.subscribe(System.out::println);
        System.out.println("end");
    }

    @Test
    public void testDeferAsync() throws Exception {
        Observable<Integer> obs = Observable.defer(() -> {
            System.out.println("start");
            return Observable.just(buildValue());
        });
        System.out.println("create");
        obs.observeOn(Schedulers.newThread())
                .subscribe(System.out::println);
        System.out.println("end");
        Thread.sleep(10);
    }

}
