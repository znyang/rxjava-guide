package com.zen.android.rxjava;

import com.zen.android.rxjava.base.Logger;

import org.junit.Test;

import rx.Observable;

/**
 * CombiningObservablesTest
 * <p>
 *
 * @author yangz
 * @version 2015/11/2
 */
public class CombiningObservablesTest {

    @Test
    public void testZip() throws Exception {
        Observable.zip(Observable.just("1", "2", "3"), Observable.just("A", "B"), (s, s2) -> s + s2)
                .subscribe(Logger::log);
    }

    @Test
    public void testSwitch() throws Exception {
        Observable.switchOnNext(Observable.just(Observable.range(1, 10), Observable.just(2, 3, 5)))
                .map(String::valueOf)
                .subscribe(Logger::log);
        Thread.sleep(1);
    }
}
