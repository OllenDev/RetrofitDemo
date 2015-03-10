package com.ollendev.retrodemo;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Chris on 3/8/15.
 *
 * http://www.mocky.io/v2/54fd0212a5dc31380837da57
 */
public interface DemoService {

    //Synchronous - Path Parameter
    @Headers({"Accept: application/json"})
    @GET("/echo/{id}")
    DemoModel getMessage(@Path("id") String id);

    //Asynchronous - Query Parameter
    @Headers({"Accept: application/json"})
    @GET("/saymyname")
    void getMessage(@Query("myname") String name, Callback<DemoModel> response);

    //Observable - Path Parameter
    @Headers({"Accept: application/json"})
    @GET("/test/{id}")
    Observable<DemoModel> getMessageRx(@Path("id") String id);

    //Asynchronous
    @Headers({"Accept: application/json"})
    @GET("/delay")
    void getSlowMessage(Callback<DemoModel> response);

    //Asynchronous Post - Body
    @Headers({"Content: application/json"})
    @POST("/echo")
    void echo(@Body String json, Callback<DemoModel> response);

}
