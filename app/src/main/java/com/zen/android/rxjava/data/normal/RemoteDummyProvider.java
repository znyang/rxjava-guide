package com.zen.android.rxjava.data.normal;

import android.os.SystemClock;
import android.util.Log;

import com.zen.android.rxjava.dummy.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * RemoteDummyProvider
 * <p>
 *
 * @author yangz
 * @version 2015/10/21
 */
public class RemoteDummyProvider implements IDummyProvider {

    private static final int COUNT = 25;

    @Override
    public List<DummyItem> createDummyItems() {
        Log.d("rxjava-guide", "createDummyItems Thread:" + Thread.currentThread().getName());
        List<DummyItem> data = new ArrayList<>();
        for (int i = COUNT; --i >= 0; ) {
            data.add(createDummyItem(COUNT - i));
        }
        return data;
    }

    private static DummyItem createDummyItem(int position) {
        SystemClock.sleep(50);
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

}
