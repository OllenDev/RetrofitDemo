package com.ollendev.retrodemo.service;

import com.ollendev.retrodemo.Constants;
import com.ollendev.retrodemo.model.TagSearch;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Custom class to manage making calls to instagram
 *
 * Created by Chris on 3/9/15.
 */
public class InstagramServiceManager {
    private static InstagramServiceManager instance;

    private List<InstagramServiceListener> listeners;
    private InstagramService service;

    private InstagramServiceManager() {
        listeners = new ArrayList<>();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.INSTAGRAM_END_POINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        service = restAdapter.create(InstagramService.class);
    }

    public static InstagramServiceManager getInstance() {
        if (instance == null) {
            instance = new InstagramServiceManager();
        }
        return instance;
    }

    public void addListener(InstagramServiceListener listener) {
        listeners.add(listener);
    }

    public void removeListener(InstagramServiceListener listener) {
        listeners.remove(listener);
    }

    public void searchTags(String tagName) {
        service.searchTags(tagName, Constants.INSTAGRAM_CLIENT_ID, new Callback<TagSearch>() {
            @Override
            public void success(TagSearch tagSearch, Response response) {
                if (response.getStatus() == 200) {
                    for (InstagramServiceListener listener : listeners) {
                        listener.onSuccess(tagSearch);
                    }
                } else {
                    for (InstagramServiceListener listener : listeners) {
                        Error error = new Error(); // TODO fill this out completely or create your own error enum preferably.
                        listener.onError(error);
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                for (InstagramServiceListener listener : listeners) {
                    Error detailedError = new Error(); // TODO fill this out completely or create your own error enum preferably.
                    listener.onError(detailedError);
                }
            }
        });
    }
}
