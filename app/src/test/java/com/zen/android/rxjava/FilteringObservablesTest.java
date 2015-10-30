package com.zen.android.rxjava;

import android.util.Log;

import com.zen.android.rxjava.base.Logger;

import org.junit.Test;

import rx.Observable;

/**
 * FilteringObservablesTest
 *
 * @author zeny
 * @version 2015.10.30
 */
public class FilteringObservablesTest {

    @Test
    public void testDebounce() throws Exception {

    }

    @Test
    public void testFilter() throws Exception {
        Observable.range(0, 10)
                .filter(n -> (n & 1) == 0)
                .map(String::valueOf)
                .subscribe(Logger::log);
    }

    @Test
    public void testTake() throws Exception {
        Observable.range(0,10)
                .take(3)
                .map(String::valueOf)
                .subscribe(Logger::log);
    }

    @Test
    public void testFirst() throws Exception {
        Observable.range(1, 3)
                .first()
                .map(String::valueOf)
                .subscribe(Logger::log);
    }

    @Test
    public void testFirstOrDefault() throws Exception {
        Observable.empty()
                .firstOrDefault(1)
                .map(String::valueOf)
                .subscribe(Logger::log);
    }
}
