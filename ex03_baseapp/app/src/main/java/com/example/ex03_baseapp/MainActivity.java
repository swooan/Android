package com.example.ex03_baseapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button button1;
    CheckBox check1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);

         button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "버튼을 눌렀어요", Toast.LENGTH_SHORT).show();
            }
         });

         check1 = (CheckBox) findViewById(R.id.check1);

         check1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(getApplicationContext(), "체크 성공", Toast.LENGTH_SHORT).show();
             }
         });

         button2 = (Button) findViewById(R.id.button2);
         button2.setBackgroundColor(Color.GRAY);
         button2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent mintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.nate.com"));
                 startActivity(mintent);
             }
         });

         button3 = (Button) findViewById(R.id.button3);
         button3.setBackgroundColor(Color.GREEN);
         button3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent mintent = new Intent(Intent.ACTION_VIEW, Uri.parse(("tel:/911")));
                 startActivity(mintent);
             }
         });

         button4 = (Button) findViewById(R.id.button4);
         button4.setBackgroundColor(Color.RED);
         button4.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent mintent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internet/images/media"));
                 startActivity(mintent);
             }
         });

         button5 = (Button) findViewById(R.id.button5);
         button5.setBackgroundColor(Color.YELLOW);
         button5.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 finish();
             }
         });
    }
}
