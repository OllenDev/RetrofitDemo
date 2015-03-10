package com.ollendev.retrodemo;

/**
 * Created by Chris on 3/9/15.
 */
public interface ResponseListener {
    public void onSuccess(DemoModel demoModel);
    public void onError(Error error);
}
