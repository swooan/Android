package com.example.final_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView img_search, img_reservation, img_qr, img_how;
    TextView txt_search, txt_reservation, txt_qr, txt_how;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main);

        Intent intent = new Intent(this, opening.class);
        startActivity(intent);

        img_search = (ImageView)findViewById(R.id.img_search);
        img_reservation = (ImageView)findViewById(R.id.img_reservation);
        img_qr = (ImageView)findViewById(R.id.img_qr);
        img_how = (ImageView)findViewById(R.id.img_how);

        txt_search = (TextView)findViewById(R.id.txt_search);
        txt_reservation = (TextView)findViewById(R.id.txt_reservation);
        txt_qr = (TextView)findViewById(R.id.txt_qr);
        txt_how = (TextView)findViewById(R.id.txt_how);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();

                ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.search");

                intent1.setComponent(name);
                startActivityForResult(intent1,101);
            }
        });

        txt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();

                ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.search");

                intent1.setComponent(name);
                startActivityForResult(intent1,101);
            }
        });

        img_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent1 = new Intent();

                ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.my_Info");

                intent1.setComponent(name);
               startActivityForResult(intent1,101);
            }
        });

        txt_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent1 = new Intent();

                ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.my_Info");

                intent1.setComponent(name);
               startActivityForResult(intent1,101);
            }
        });

        img_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();

                ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.qr");

                intent1.setComponent(name);
                startActivityForResult(intent1, 101);
            }
        });

        txt_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();

                ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.qr");

                intent1.setComponent(name);
                startActivityForResult(intent1, 101);
            }
        });

        img_how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();

                ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.how");

                intent1.setComponent(name);
                startActivityForResult(intent1, 101);
            }
        });

        txt_how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();

                ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.how");

                intent1.setComponent(name);
                startActivityForResult(intent1, 101);
            }
        });
    }
}
