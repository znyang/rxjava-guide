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
        for (int i = 0; i < level; i++) {
            total += data[i];
        }
        long cost = System.currentTimeMillis() - start;
        System.out.println("cost1 " + cost);

        start = System.currentTimeMillis();
        Observable.just(1, 2, 3)
                .subscribe(r -> {
                    //r++;
                });
        cost = System.currentTimeMillis() - start;
        System.out.println("cost2 " + cost);
    }
}
