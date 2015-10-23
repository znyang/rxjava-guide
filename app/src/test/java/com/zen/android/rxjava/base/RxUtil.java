package com.zen.android.rxjava.base;

import rx.Observable;

/**
 * RxUtil
 * <p>
 *
 * @author yangz
 * @version 2015/10/23
 */
public class RxUtil {

    public static <T> T gen(T src) {
        Logger.log("generate " + src);
        return src;
    }

}
