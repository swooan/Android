package com.example.final_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import fragment.sidebar3;
import model.ShopVo;
import res.DBConnection.Custom_Adapter;
import res.DBConnection.Web;
import res.detail_VPAdapter;
import res.network.NetworkSearch;
import res.resources;

public class shop_detail extends AppCompatActivity {

    private sidebar3 sidebar = new sidebar3(this);
    public ArrayList<ShopVo> shop_info_list;
    Bitmap bitmap, bitmap2;
    String shop_img1, shop_img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_detail);

        outsidebar = findViewById(R.id.out_sidebar);
        mainLayout = findViewById(R.id.id_main);
        viewLayout = findViewById(R.id.fl_silde);
        sideLayout = findViewById(R.id.view_sildebar);

        getSupportFragmentManager().beginTransaction().add(R.id.view_sildebar, sidebar).commit();

        ImageButton side_menu = (ImageButton)findViewById(R.id.btn_sidebar8);
        side_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sidebar.showMenu();
            }
        });

        shop_info_list = (ArrayList<ShopVo>) resources.gets.get("shop_info_list");

        //Toast.makeText(this, "가게이름 : "+shop_info_list.get(0).getShop_title()+"\n에 입장하였습니다", Toast.LENGTH_LONG).show();

        TabLayout tabLayout = findViewById(R.id.detail_tab);

        tabLayout.addTab(tabLayout.newTab().setText("가게 정보"));
        tabLayout.addTab(tabLayout.newTab().setText("예약 정보"));
        tabLayout.addTab(tabLayout.newTab().setText("리 뷰"));
        tabLayout.addTab(tabLayout.newTab().setText("지 도"));

        tabLayout.setTabGravity((TabLayout.GRAVITY_FILL));

        final ViewPager viewPager = findViewById(R.id.viewpager);
        final detail_VPAdapter adapter = new detail_VPAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        viewPager.setOffscreenPageLimit(3);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 2 && (Boolean) resources.gets.get("review_is_empty")){
                    Toast.makeText(mContext, "등록된 리뷰가 없습니다", Toast.LENGTH_SHORT).show();
                }
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        ImageView img1 = (ImageView)findViewById(R.id.imageView);
        ImageView img2 = (ImageView)findViewById(R.id.imageView2);
        shop_img1 = shop_info_list.get(0).getShop_photo();
        shop_img2 = shop_info_list.get(0).getShop_subphoto();
        try {
            shop_img1 = shop_img1.substring(0, shop_img1.indexOf("/"));
        }catch (Exception e){
            shop_img1 = "-";
        }

        try {
            shop_img2 = shop_img2.substring(0, shop_img2.indexOf("/"));
        }catch (Exception e){
            shop_img2="-";
        }

        Thread uThread = new Thread() {
            @Override
            public void run(){
                try{
                    if(!shop_img1.equals("-")){
                        URL url = new URL(Web.servletImgURL+shop_img1);
                        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                        conn.setDoInput(true);
                        conn.connect();

                        InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                        bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환
                    }

                    if(!shop_img2.equals("-")){
                        URL url2 = new URL(Web.servletSubImgURL+shop_img2);
                        HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();
                        conn2.setDoInput(true);
                        conn2.connect();

                        InputStream is2 = conn2.getInputStream(); //inputStream 값 가져오기
                        bitmap2 = BitmapFactory.decodeStream(is2); // Bitmap으로 반환
                    }

                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        };
        uThread.start(); // 작업 Thread 실행
        try{
            uThread.join();
            if(bitmap != null){
                img1.setImageBitmap(bitmap);
            }
            if(bitmap2 != null){
                img2.setImageBitmap(bitmap2);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    private String TAG = "shop_detail";

    private Context mContext = shop_detail.this;

    private ViewGroup mainLayout;   //사이드 나왔을때 클릭방지할 영역
    private ViewGroup viewLayout;   //전체 감싸는 영역
    private ViewGroup sideLayout;   //사이드바만 감싸는 영역
    private ViewGroup outsidebar;

    private Boolean isMenuShow = false;
    private Boolean isExitFlag = false;

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

