package com.zen.android.rxjava.data.obs;

import com.zen.android.rxjava.dummy.DummyItem;

import java.util.List;

import rx.Observable;

/**
 * IObsDummyProvider
 * <p>
 *
 * @author yangz
 * @version 2015/10/21
 */
public interface IObsDummyProvider {

    Observable<List<DummyItem>> createDummyItems();

}
