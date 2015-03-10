package com.ollendev.retrodemo.service;

import com.ollendev.retrodemo.model.TagSearch;

public interface InstagramServiceListener {
    public void onSuccess(TagSearch tagSearch);
    public void onError(Error error);
}
