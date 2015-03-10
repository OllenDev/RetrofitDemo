package com.ollendev.retrodemo.service;

import com.ollendev.retrodemo.model.TagSearch;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

public interface InstagramService {
    @Headers({"Accept: application/json"})
    @GET("/v1/tags/search")
    void searchTags(@Query("q") String tagname, @Query("client_id") String clientId, Callback<TagSearch> callback);

}
