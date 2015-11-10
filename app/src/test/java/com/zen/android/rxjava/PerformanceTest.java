package com.zen.android.rxjava;

import org.junit.Test;

import rx.Observable;

/**
 * PerformanceTest
 *
 * @author zeny
 * @version 2015.11.07
 */
public class PerformanceTest {

    @Test
    public void testFrom() throws Exception {
        final int level = 10;
        Integer[] data = new Integer[level];
        for (int i = level; --i >= 0; ) {
            data[i] = i;
        }
        long start = System.currentTimeMillis();

        int total = 0;
        for (int d : data) {
            total += d;
        }
        long cost = System.currentTimeMillis() - start;
        System.out.println("foreach cost: " + cost);
    }

    @Test
    public void testReactive() throws Exception {
        long start = System.currentTimeMillis();
        Observable.range(0, 10)
                .subscribe(r -> {
                    //r++;
                });
        long cost = System.currentTimeMillis() - start;
        System.out.println("reactive cost: " + cost);
    }
}
