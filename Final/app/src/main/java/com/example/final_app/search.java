package com.example.final_app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import model.ShopVo;
import res.DBConnection.Custom_Adapter;
import res.network.NetworkSearch;


public class search extends AppCompatActivity implements View.OnClickListener {

    View view;

    private ViewGroup mainLayout;   //사이드 나왔을때 클릭방지할 영역
    private ViewGroup viewLayout;   //전체 감싸는 영역
    private ViewGroup sideLayout;   //사이드바만 감싸는 영역
    private ViewGroup outsidebar;

    private Boolean isMenuShow = false;
    private Boolean isExitFlag = false;

    private String TAG = "search";

    private Context mContext = search.this;

    private ListView listView;
    private Custom_Adapter adapter;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

        if (isMenuShow) {
            closeMenu();
        }
        else{
            finish();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        findViewById(R.id.btn_sidebar7).setOnClickListener(this);

        outsidebar = findViewById(R.id.out_sidebar);
        mainLayout = findViewById(R.id.id_main);
        viewLayout = findViewById(R.id.fl_silde);
        sideLayout = findViewById(R.id.view_sildebar);
        listView = (ListView)findViewById(R.id.listView);


        addSideView();

        Intent intent = new Intent(this, Loading.class);
        startActivity(intent);

        // 어댑터 만들기 -> 3요소 필요 (위치, 디자인, 데이터)
        adapter = new Custom_Adapter(search.this, //위치
                R.layout.adapter_search, // 디자인
                new ArrayList<ShopVo>()); // 데이터

        listView.setAdapter(adapter);

        new NetworkSearch((Custom_Adapter) listView.getAdapter()).execute(""); // 전체 불러오기

        /*view = (View)findViewById(R.id.toTheDetail1);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.shop_detail");

                intent.setComponent(name);
                startActivityForResult(intent, 101);
            }
        });*/
    }
    private void addSideView(){

        sidebar sidebar = new sidebar(mContext);
        sideLayout.addView(sidebar);

        viewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        outsidebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeMenu();
            }
        });

        sidebar.setEventListener(new sidebar.EventListener() {

            @Override
            public void btnCancel() {
                Log.e(TAG, "btnCancel");
                closeMenu();
            }

            @Override
            public void btnLevel1() {
                Log.e(TAG, "btnLevel1");

                closeMenu();
            }

            @Override
            public void btnLogin(){
                Log.e(TAG, "btnLogin");

                closeMenu();

                Intent intent1 = new Intent();

                ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.Login");

                intent1.setComponent(name);
                startActivityForResult(intent1, 101);
            }
        });
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_sidebar7 :

                showMenu();
                break;
        }

    }

    public void closeMenu(){

        isMenuShow = false;
        Animation slide = AnimationUtils.loadAnimation(mContext, R.anim.sidebar_hidden);
        sideLayout.startAnimation(slide);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewLayout.setVisibility(View.GONE);
                viewLayout.setEnabled(false);
                mainLayout.setEnabled(true);
            }
        }, 450);
    }

    public void showMenu(){

        isMenuShow = true;
        Animation slide = AnimationUtils.loadAnimation(this, R.anim.sidebar_show);
        sideLayout.startAnimation(slide);
        viewLayout.setVisibility(View.VISIBLE);
        viewLayout.setEnabled(true);
        mainLayout.setEnabled(false);
        Log.e(TAG, "메뉴버튼 클릭");
    }
}

