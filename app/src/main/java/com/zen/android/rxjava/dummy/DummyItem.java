package com.zen.android.rxjava.dummy;

import java.io.Serializable;

/**
 * A dummy item representing a piece of content.
 */
public class DummyItem implements Serializable{

    public String id;
    public String content;
    public String details;

    public DummyItem(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    @Override
    public String toString() {
        return content;
    }
}
