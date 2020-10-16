package com.example.final_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fragment.sidebar3;
import res.resources;


public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    private Context mContext = MainActivity.this;

    private ViewGroup mainLayout;   //사이드 나왔을때 클릭방지할 영역
    private ViewGroup viewLayout;   //전체 감싸는 영역
    private ViewGroup sideLayout;   //사이드바만 감싸는 영역
    private ViewGroup outsidebar;

    private Boolean isMenuShow = false;
    private Boolean isExitFlag = false;

    ImageButton side_menu;

    sidebar3 sidebar = new sidebar3();


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
            sidebar.closeMenu();
            /*closeMenu();*/
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

    ImageView img_search, img_reservation, img_qr, img_how, img_search_location;
    TextView txt_search, txt_reservation, txt_qr, txt_how;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main);
        resources.gets.put("login", false);
        resources.gets.put("sidebar", sidebar);


        Intent intent = new Intent(this, opening.class);
        startActivity(intent);

        viewLayout = findViewById(R.id.fl_silde);
        sideLayout = findViewById(R.id.view_sildebar);
        mainLayout = findViewById(R.id.id_main);
        outsidebar = findViewById(R.id.out_sidebar);

        getSupportFragmentManager().beginTransaction().replace(R.id.view_sildebar, sidebar).detach(sidebar).attach(sidebar).commit();


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
                resources.gets.put("flag", true);
                Intent intent1 = new Intent();

                ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.search_menu");

                intent1.setComponent(name);
                startActivityForResult(intent1, 101);
            }
        });

        txt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resources.gets.put("flag", true);

                Intent intent1 = new Intent();

                ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.search_menu");

                intent1.setComponent(name);
                startActivityForResult(intent1, 101);

            }
        });

        img_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((Boolean) resources.gets.get("login")) {
                    Intent intent1 = new Intent();

                    ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.my_Info");

                    intent1.setComponent(name);
                    startActivityForResult(intent1, 101);
                } else {
                        Toast.makeText(MainActivity.this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((Boolean) resources.gets.get("login")) {
                    Intent intent1 = new Intent();

                    ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.my_Info");

                    intent1.setComponent(name);
                    startActivityForResult(intent1, 101);
                } else {
                    Toast.makeText(MainActivity.this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((Boolean) resources.gets.get("login")) {
                    Intent intent1 = new Intent();

                    ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.qr");

                    intent1.setComponent(name);
                    startActivityForResult(intent1, 101);
                } else {
                    Toast.makeText(MainActivity.this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((Boolean) resources.gets.get("login")) {
                    Intent intent1 = new Intent();

                    ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.qr");

                    intent1.setComponent(name);
                    startActivityForResult(intent1, 101);
                } else {
                    Toast.makeText(MainActivity.this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((Boolean) resources.gets.get("login")) {
                    Intent intent1 = new Intent();

                    ComponentName name = new ComponentName("com.example.final_app", "com.example.final_app.how");

                    intent1.setComponent(name);
                    startActivityForResult(intent1, 101);
                } else {
                    Toast.makeText(MainActivity.this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
                }
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

        if(isMenuShow == true) {
            outsidebar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sidebar.closeMenu();
                    Log.i("outsidebar"," : 눌렸습니다.");
                }
            });
        }

        side_menu = (ImageButton)findViewById(R.id.btn_sidebar1);
        side_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sidebar.showMenu();
            }
        });
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
