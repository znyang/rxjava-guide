package com.zen.android.rxjava.data.obs;

import com.zen.android.rxjava.data.IDummyProvider;
import com.zen.android.rxjava.data.RemoteDummyProvider;
import com.zen.android.rxjava.dummy.DummyItem;

import java.util.List;

import rx.Observable;

/**
 * ObsRemoteDummyProvider
 * <p>
 *
 * @author yangz
 * @version 2015/10/21
 */
public class ObsRemoteDummyProvider implements IObsDummyProvider {
    @Override
    public Observable<List<DummyItem>> createDummyItems() {
        return Observable.defer(() -> Observable.just(new RemoteDummyProvider().createDummyItems()));
    }
}
