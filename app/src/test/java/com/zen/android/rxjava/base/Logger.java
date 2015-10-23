package com.zen.android.rxjava.base;

import java.util.Date;

/**
 * Logger
 * <p>
 *
 * @author yangz
 * @version 2015/10/23
 */
public class Logger {

    public static void log(String src) {
        System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " " + src);
    }

    public static void print(String src) {
        System.out.println(src);
    }

}
