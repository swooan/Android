package com.example.final_app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class search extends AppCompatActivity {

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        Intent intent = new Intent(this, Loading.class);
        startActivity(intent);

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

}
