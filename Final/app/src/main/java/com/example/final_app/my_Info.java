package com.example.final_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

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
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import model.ShopUserVo;
import res.my_info_VPAdapter;
import fragment.sidebar3;
import res.resources;

public class my_Info extends AppCompatActivity implements resources{

    private String TAG = "my_info";

    private Context mContext = my_Info.this;

    private ViewGroup mainLayout;   //사이드 나왔을때 클릭방지할 영역
    private ViewGroup viewLayout;   //전체 감싸는 영역
    private ViewGroup sideLayout;   //사이드바만 감싸는 영역
    private ViewGroup outsidebar;

    private Boolean isMenuShow = false;
    private Boolean isExitFlag = false;

    private sidebar3 sidebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_info);

        outsidebar = findViewById(R.id.out_sidebar);
        mainLayout = findViewById(R.id.id_main);
        viewLayout = findViewById(R.id.fl_silde);
        sideLayout = findViewById(R.id.view_sildebar);

        sidebar = new sidebar3(this);

        getSupportFragmentManager().beginTransaction().add(R.id.view_sildebar, sidebar).commit();
        ImageButton side_menu = (ImageButton)findViewById(R.id.btn_sidebar4);
        side_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sidebar.showMenu();
            }
        });


        TabLayout tab = findViewById(R.id.my_info_tab);

        tab.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager view = findViewById(R.id.my_info_view);
        final res.my_info_VPAdapter adapter = new my_info_VPAdapter
                (getSupportFragmentManager(), tab.getTabCount());

        view.setAdapter(adapter);

        view.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 1 && !(Boolean) resources.gets.get("payment_info")){
                    Toast.makeText(my_Info.this, "결제 내역이 없습니다", Toast.LENGTH_SHORT).show();
                }
                view.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



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
        else {
            finish();
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
