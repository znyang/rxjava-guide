package com.zen.android.rxjava;

import android.util.Log;

import com.zen.android.rxjava.base.Logger;

import junit.framework.TestCase;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;

/**
 * FilteringObservablesTest
 *
 * @author zeny
 * @version 2015.10.30
 */
public class FilteringObservablesTest extends TestCase {

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
        Observable.range(0, 10)
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

    @Test
    public void testSkip() throws Exception {
        Observable.range(0, 10)
                .skip(4)
                .map(String::valueOf)
                .subscribe(Logger::log);
    }

    @Test
    public void testElementAt() throws Exception {
        Observable.range(0, 10)
                .elementAt(3)
                .subscribe(integer -> {
                    assertEquals(integer.intValue(), 3);
                });
    }
}
