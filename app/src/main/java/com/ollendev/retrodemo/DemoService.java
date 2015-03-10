package com.ollendev.retrodemo;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Chris on 3/8/15.
 *
 * http://www.mocky.io/v2/54fd0212a5dc31380837da57
 */
public interface DemoService {

    //Synchronous
    @Headers({"Accept: application/json"})
    @GET("/v2/{id}")
    Response getMessage(@Path("id") String id);

    //Asynchronous
    @Headers({"Accept: application/json"})
    @GET("/v2/{id}")
    void getMessage(@Path("id") String id, Callback<Response> response);

    //Observable
    @Headers({"Accept: application/json"})
    @GET("/v2/{id}")
    Observable<Response> getMessageRx(@Path("id") String id);

    //Asynchronous
    @Headers({"Accept: application/json"})
    @GET("/delay")
    void getSlowMessage(Callback<Response> response);
}
