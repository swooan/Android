package com.example.final_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class qr extends AppCompatActivity {

    /* QR code scanner 객체 */
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* QR code Scanner Setting */
        qrScan = new IntentIntegrator(this);
        qrScan.setPrompt("QR 코드를 사각형 안에 인식시켜주세요");
        qrScan.setBeepEnabled(false);
        qrScan.setCaptureActivity(CaptureActivity.class);
        qrScan.initiateScan();

    }

    @Override
    public void onBackPressed(){
        finish();
    }

    /* Getting the Scan Results */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Log.v("qrcode :::::::::::", "no contents");
                finish();
            } else { //QR코드, 내용 존재
                try {
                    /* QR 코드 내용*/
                    String temp = result.getContents();

                    /* 로직
                     *
                     * 로직 끝 */

                    Log.v("qrcode Contents :::::", temp);
                    Toast.makeText(getApplicationContext(), result.getContents(), Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.v("Exception :::::::::::::", "QR code fail");

                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}