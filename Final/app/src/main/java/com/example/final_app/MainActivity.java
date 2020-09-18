package com.example.final_app;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "MainActivity";

    private Context mContext = MainActivity.this;

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

        if(isMenuShow){
            closeMenu();
        }else{

            if(isExitFlag){
                finish();
            } else {

                isExitFlag = true;
                Toast.makeText(this, "뒤로가기를 한번더 누르시면 앱이 종료됩니다.",  Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExitFlag = false;
                    }
                }, 2000);
            }
        }
    }

    ImageView img_search, img_reservation, img_qr, img_how;
    TextView txt_search, txt_reservation, txt_qr, txt_how;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main);

        findViewById(R.id.btn_sidebar1).setOnClickListener(this);

        outsidebar = findViewById(R.id.out_sidebar);
        mainLayout = findViewById(R.id.id_main);
        viewLayout = findViewById(R.id.fl_silde);
        sideLayout = findViewById(R.id.view_sildebar);


        addSideView();

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

            case R.id.btn_sidebar1 :

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
