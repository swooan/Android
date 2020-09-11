package com.example.final_app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import res.LoadingActivity;

public class search extends LoadingActivity {

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        startProgress();

        view = (View)findViewById(R.id.toTheDetail1);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.shop_detail");

                intent.setComponent(name);
                startActivityForResult(intent, 101);
            }
        });
    }

    private void startProgress() {

        progressON("로딩중......");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressOFF();
            }
        }, 3500);
    }
}
