package com.example.final_app;

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
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import fragment.sidebar3;

import fragment.reservation_fragment.reserv_complete;
import fragment.reservation_fragment.reserv_order;
import fragment.reservation_fragment.reserv_payment;
import fragment.reservation_fragment.reserv_simple;
import model.ShopVo;
import res.resources;

public class reservation extends AppCompatActivity{

    reserv_simple simple;
    reserv_order order;
    reserv_complete complete;
    reserv_payment payment;

    private ViewGroup mainLayout;   //사이드 나왔을때 클릭방지할 영역
    private ViewGroup viewLayout;   //전체 감싸는 영역
    private ViewGroup sideLayout;   //사이드바만 감싸는 영역
    private ViewGroup outsidebar;
    private sidebar3 sidebar = new sidebar3(this);

    private Boolean isMenuShow = false;
    private Boolean isExitFlag = false;

    private String TAG = "reservation";

    private Context mContext = reservation.this;
    public String shop_title, shop_id;
    public ArrayList<String> tempArr = new ArrayList<String>();

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
            sidebar.closeMenu();
        }
        else{
            finish();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        outsidebar = findViewById(R.id.out_sidebar);
        mainLayout = findViewById(R.id.id_main);
        viewLayout = findViewById(R.id.fl_silde);
        sideLayout = findViewById(R.id.view_sildebar);
        shop_title = getIntent().getExtras().getString("shop_title");
        shop_id = getIntent().getExtras().getString("shop_id");

        getSupportFragmentManager().beginTransaction().add(R.id.view_sildebar, sidebar).commit();

        ImageButton side_menu = (ImageButton)findViewById(R.id.btn_sidebar6);
        side_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sidebar.showMenu();
            }
        });

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

    public void closeMenu(Animation animation) {
        isMenuShow = false;
        sideLayout.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewLayout.setVisibility(View.GONE);
                viewLayout.setClickable(false);
                mainLayout.setClickable(true);
            }
        }, 450);
    }

    public void showMenu(Animation animation){

        isMenuShow = true;
        sideLayout.startAnimation(animation);
        viewLayout.setVisibility(View.VISIBLE);
        viewLayout.setClickable(true);
        mainLayout.setClickable(false);
        Log.e(TAG, "메뉴버튼 클릭");
    }
}
