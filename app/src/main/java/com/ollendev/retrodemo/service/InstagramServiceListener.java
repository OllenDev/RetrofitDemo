package com.ollendev.retrodemo.service;

import com.ollendev.retrodemo.model.TagSearch;

/**
 * Created by Chris on 3/9/15.
 */
public interface InstagramServiceListener {
    public void onSuccess(TagSearch tagSearch);
    public void onError(Error error);
}
