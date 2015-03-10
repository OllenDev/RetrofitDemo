package com.ollendev.retrodemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import rx.Observable;
import rx.functions.Action1;


public class MainActivity extends Activity {
    private static String TAG = MainActivity.class.getSimpleName();
    DemoService service;
    Observable<Response> observableResponse;
    String apiId = "54fd0212a5dc31380837da57";
//    String endPoint = "http://www.mocky.io";
    String endPoint = "https://peaceful-brook-1682.herokuapp.com";

    @InjectView(R.id.contentView) TextView contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .build();
        service = restAdapter.create(DemoService.class);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @OnClick(R.id.openActivityButton)
    public void openActivity(View view) {
        Intent intent = new Intent(this, BlankActivity.class);
        this.startActivity(intent);
    }

    @OnClick(R.id.sendSyncButton)
    public void sendSync(View view) {
        Log.d(TAG, "send");
        Response rep = service.getMessage(apiId);
        Log.d(TAG, "response received");
        contentView.setText(rep.message);
    }

    @OnClick(R.id.sendAsyncButton)
    public void sendAsyncButton(View view) {
        service.getMessage(apiId, new Callback<Response>() {
            @Override
            public void success(Response response, retrofit.client.Response response2) {
                MainActivity.this.contentView.setText(response.message);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
                MainActivity.this.contentView.setText(error.getMessage());
            }
        });
    }

    @OnClick(R.id.sendRxButton)
    public void sendRx(View view) {
        observableResponse = service.getMessageRx(apiId);
        observableResponse.subscribe(new Action1<Response>() {
                    @Override
                    public void call(Response response) {
                        MainActivity.this.contentView.setText(response.message);
                    }
                });
    }
}
