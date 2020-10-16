package com.example.final_app;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import model.ShopVo;
import res.DBConnection.Custom_Adapter;
import res.network.NetworkSearch;


public class qr extends AppCompatActivity {

    /* QR code scanner 객체 */
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Log.v("qrcode :::::::::::", "no contents");
                finish();
            } else { //QR코드, 내용 존재
                try {
                    String shop_id = result.getContents().substring(0, result.getContents().indexOf(","));
                    String table_num = result.getContents().substring(result.getContents().indexOf(",") + 1);
                    new NetworkSearch(this).execute("1",shop_id, table_num); // shopList 목록 전체 불러오기
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "등록되지 않은 가게이거나 qr코드 인식이 잘못되었습니다", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}