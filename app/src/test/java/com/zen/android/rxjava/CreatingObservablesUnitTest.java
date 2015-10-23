package com.zen.android.rxjava;

import android.support.annotation.NonNull;

import com.zen.android.rxjava.base.IConfig;
import com.zen.android.rxjava.base.Logger;
import com.zen.android.rxjava.base.RxUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = IConfig.EMULATE_SDK, manifest = IConfig.MANIFEST)
public class CreatingObservablesUnitTest {

    /**
     * create
     * <p>
     * create an Observable from scratch by calling observer methods programmatically
     *
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception {
        Logger.log("testCreate");
        final boolean hasError = false;

        Observable<String> obs = buildObservableByCreate(hasError);
        obs.subscribe(
                s -> Logger.log("onNext - " + s),
                e -> {
                    Logger.log("onError");
                    assertTrue(hasError);
                },
                () -> {
                    Logger.log("onCompleted");
                    assertFalse(hasError);
                });
    }

    @NonNull
    private Observable<String> buildObservableByCreate(boolean hasError) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext("1");
                    subscriber.onNext("2");
                    if (hasError) {
                        emitsError();
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    private void emitsError() {
        throw new IllegalArgumentException();
    }

    /**
     * convert an object or a set of objects into an Observable that emits that or those objects
     *
     * @throws Exception
     */
    @Test
    public void testJustOperator() throws Exception {
        Logger.log("testJustOperator");
        Observable<String> obs = Observable.just("1", "2", "3");
        obs.subscribe(Logger::log);
    }

    /**
     * convert some other object or data structure into an Observable
     *
     * @throws Exception
     */
    @Test
    public void testFromOperator() throws Exception {
        Logger.log("testFromOperator");
        List<String> data = Arrays.asList("1", "2", "3");
        Observable.from(data)
                .subscribe(Logger::log);
    }

    /**
     * do not create the Observable until the observer subscribes, and create a fresh Observable for each observer
     *
     * @throws Exception
     */
    @Test
    public void testDeferOperator() throws Exception {
        Logger.log("testDeferOperator");
        Observable<String> obs = Observable.defer(() -> Observable.just(RxUtil.gen("1"), RxUtil.gen("2")));
        obs.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(Logger::log);
        Thread.sleep(200);
    }

    /**
     * create an Observable that emits a sequence of integers spaced by a particular time interval
     *
     * @throws Exception
     */
    @Test
    public void testInterval() throws Exception {
        Logger.log("testInterval");
        final int count = 3;
        CountDownLatch latch = new CountDownLatch(count);

        Subscription subscription = Observable.interval(1, TimeUnit.MILLISECONDS)
                .subscribe(time -> {
                    latch.countDown();
                    Logger.log(String.valueOf(time));
                });
        latch.await();
        subscription.unsubscribe();
    }

    @Test
    public void testRange() throws Exception {
        Observable.range(2, 3)
                .subscribe(integer -> {
                    Logger.log("" + integer);
                });
    }

    @Test
    public void testRepeat() throws Exception {
        Observable.just("1", "2").repeat(3)
                .subscribe(Logger::log);
    }

    @Test
    public void testStart() throws Exception {
        Observable.just("111", "234", "256", "313")
                .startWith("000", "001")
                .subscribe(Logger::log);
    }

    @Test
    public void testTimer() throws Exception {
        Logger.log("testTimer");
        CountDownLatch latch = new CountDownLatch(1);
        Observable.timer(100, TimeUnit.MILLISECONDS)
                .subscribe(i -> {
                    Logger.log("" + i);
                    latch.countDown();
                });
        latch.await();
    }
}