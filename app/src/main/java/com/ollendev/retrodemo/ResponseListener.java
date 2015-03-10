package com.ollendev.retrodemo;

/**
 * Created by Chris on 3/9/15.
 */
public interface ResponseListener {
    public void onSuccess(Response response);
    public void onError(Error error);
}
