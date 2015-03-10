package com.ollendev.retrodemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ollendev.retrodemo.model.TagInfo;
import com.ollendev.retrodemo.model.TagSearch;
import com.ollendev.retrodemo.service.InstagramService;
import com.ollendev.retrodemo.service.InstagramServiceListener;
import com.ollendev.retrodemo.service.InstagramServiceManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import rx.Observable;
import rx.functions.Action1;


public class MainActivity extends Activity implements InstagramServiceListener {
    private static String TAG = MainActivity.class.getSimpleName();
    private String searchTag = null;

    @InjectView(R.id.contentView) TextView contentView;
    @InjectView(R.id.searchEditText) EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        InstagramServiceManager.getInstance().addListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        InstagramServiceManager.getInstance().removeListener(this);
    }

    @OnClick(R.id.searchButton)
    public void search(View view) {
        Log.d(TAG, "searching for tag");
        this.searchTag = searchEditText.getText().toString();
        InstagramServiceManager.getInstance().searchTags(this.searchTag);
    }

    @Override
    public void onSuccess(TagSearch tagSearch) {
        Log.d(TAG, "Search for Tag Succeeded");
        tellHowManyTags(tagSearch);
    }

    @Override
    public void onError(Error error) {
        Log.d(TAG, "Search for Tag Failed");
        contentView.setText("Error");
    }

    private void tellHowManyTags(TagSearch tagSearch) {
        String updateText = "Couldn't find any tag named " + tagSearch + ".";
        if (tagSearch != null) {
            for (TagInfo tagInfo : tagSearch.tagList) {
                if (tagInfo.name.equals(searchTag)) {
                    updateText = searchTag + " has " + tagInfo.count.toString() + " pics.";
                }
            }
        }
        contentView.setText(updateText);
    }
}
