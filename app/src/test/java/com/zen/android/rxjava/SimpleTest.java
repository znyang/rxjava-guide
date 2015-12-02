package com.zen.android.rxjava;

import org.junit.Test;

import java.util.Iterator;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * SimpleTest
 *
 * @author zeny
 * @version 2015.11.30
 */
public class SimpleTest {

    private void log(String log) {
        System.out.println(Thread.currentThread().getName() + " - " + log);
    }

    private String buildValue() {
        log("build");
        return "1";
    }

    @Test
    public void testJust() throws Exception {
        Observable<String> obs = Observable.just(buildValue());
        log("create");
        obs.subscribe(this::log);
        log("end");
    }

    @Test
    public void testCreate() throws Exception {
        Observable<String> obs = Observable.create(subscriber -> {
            subscriber.onNext(buildValue());
            subscriber.onCompleted();
        });
        log("create");
        obs.subscribe(this::log);
        log("end");
    }

    @Test
    public void testDefer() throws Exception {
        Observable<String> obs = Observable.defer(
                () -> Observable.just(buildValue()));
        log("create");
        obs.subscribe(this::log);
        log("end");
    }

    @Test
    public void testDeferAsync() throws Exception {
        Observable<String> obs = Observable.defer(
                () -> Observable.just(buildValue()));
        log("create");
        obs.subscribeOn(Schedulers.newThread())
                .subscribe(this::log);
        log("end");
        Thread.sleep(10);
    }

    @Test
    public void testDeferSync() throws Exception {
        Observable<String> obs = Observable.defer(
                () -> Observable.just(buildValue(), buildValue()));
        log("create");
        Iterable<String> result = obs.subscribeOn(Schedulers.newThread())
                .toBlocking().toIterable();
        Observable.from(result)
                .subscribe(this::log);
        log("end");
    }

    @Test
    public void testConcat() throws Exception {
    Observable<String> disk = Observable.defer(() -> Observable.just(localData()));
    Observable<String> network = Observable.defer(() -> Observable.just(remoteData()));

    // 产生两次值事件，分别访问本地、网络
    Observable.concat(disk, network).subscribe();
    // 只产生一个值，如果本地没有就取网络
    Observable.concat(disk, network).first().subscribe();
    }

    private String localData() {
        return "1";
    }

    private String remoteData() {
        return "2";
    }
}
