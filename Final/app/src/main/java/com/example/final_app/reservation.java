package com.example.final_app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import fragment.reservation_fragment.reserv_complete;
import fragment.reservation_fragment.reserv_order;
import fragment.reservation_fragment.reserv_payment;
import fragment.reservation_fragment.reserv_simple;

public class reservation extends AppCompatActivity implements View.OnClickListener{

    reserv_simple simple;
    reserv_order order;
    reserv_complete complete;
    reserv_payment payment;

    private ViewGroup mainLayout;   //사이드 나왔을때 클릭방지할 영역
    private ViewGroup viewLayout;   //전체 감싸는 영역
    private ViewGroup sideLayout;   //사이드바만 감싸는 영역
    private ViewGroup outsidebar;

    private Boolean isMenuShow = false;
    private Boolean isExitFlag = false;

    private String TAG = "reservation";

    private Context mContext = reservation.this;

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
        setContentView(R.layout.reservation);

        findViewById(R.id.btn_sidebar6).setOnClickListener(this);

        outsidebar = findViewById(R.id.out_sidebar);
        mainLayout = findViewById(R.id.id_main);
        viewLayout = findViewById(R.id.fl_silde);
        sideLayout = findViewById(R.id.view_sildebar);


        addSideView();

        simple = new reserv_simple();
        order = new reserv_order();
        complete = new reserv_complete();
        payment = new reserv_payment();

        getSupportFragmentManager().beginTransaction().replace(R.id.reserv_frame,simple).commit();
    }

    public void onChangeFragment(int index) {
        if(index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.reserv_frame,simple).commit();
        }
        else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.reserv_frame,order).commit();
        }
        else if (index == 2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.reserv_frame,payment).commit();
        }
        else if (index == 3) {
            getSupportFragmentManager().beginTransaction().replace(R.id.reserv_frame,complete).commit();
        }
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
        });
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_sidebar6 :

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
