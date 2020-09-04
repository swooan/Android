package com.example.final_app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class opening extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening);
        startLoading();
    }
    private  void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }
}
