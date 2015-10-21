package com.zen.android.rxjava.data;

import com.zen.android.rxjava.dummy.DummyItem;

import java.util.List;

/**
 * IDummyProvider
 * <p/>
 *
 * @author yangz
 * @version 2015/10/21
 */
public interface IDummyProvider {

    List<DummyItem> createDummyItems();

}
