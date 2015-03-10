package com.ollendev.retrodemo;

import android.app.Activity;
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


public class SecondActivity extends Activity {
    private static final String TAG = SecondActivity.class.getSimpleName();
    String endPoint = "https://peaceful-brook-1682.herokuapp.com";

    @InjectView(R.id.response) TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.sendSlowButton)
    public void sendSlow(View view) {
        Log.d(TAG, "Sending Slow Message");
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .build();
        DemoService service = restAdapter.create(DemoService.class);
        service.getSlowMessage(new Callback<DemoModel>() {
            @Override
            public void success(DemoModel demoModel, retrofit.client.Response response2) {
                Log.d(TAG, "Got slow response");
                responseTextView.setText(demoModel.message);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Got slow error");
                Log.e(TAG, error.getMessage());
            }
        });
    }
}
