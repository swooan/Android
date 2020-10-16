package com.example.final_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import model.ShopVo;
import res.DBConnection.Custom_Adapter;
import res.network.NetworkPhoto;
import res.network.NetworkSearch;
import fragment.sidebar3;
import res.resources;


public class search extends AppCompatActivity implements resources{

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
    private sidebar3 sidebar = new sidebar3(this);
    TextView searchTV;

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
        setContentView(R.layout.search);

        outsidebar = findViewById(R.id.out_sidebar);
        mainLayout = findViewById(R.id.id_main);
        viewLayout = findViewById(R.id.fl_silde);
        sideLayout = findViewById(R.id.view_sildebar);
        listView = (ListView) findViewById(R.id.listView);
        searchTV = (TextView) findViewById(R.id.search);

        final int menu = getIntent().getExtras().getInt("menu");

        final Spinner loc = (Spinner) findViewById(R.id.loc);


        final Spinner loc_detail = (Spinner) findViewById(R.id.loc_detail);
        if (menu == 1){
        loc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    loc_detail.setVisibility(View.GONE);
                }else{
                    if (position == 1) {
                        ArrayAdapter s1 = new ArrayAdapter<String>(search.this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.location1_1));
                        s1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        loc_detail.setAdapter(s1);
                    } else if (position == 2) {
                        ArrayAdapter s2 = new ArrayAdapter<String>(search.this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.location1_2));
                        s2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        loc_detail.setAdapter(s2);
                    } else if (position == 3) {
                        ArrayAdapter s3 = new ArrayAdapter<String>(search.this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.location1_3));
                        s3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        loc_detail.setAdapter(s3);
                    } else if (position == 4) {
                        ArrayAdapter s4 = new ArrayAdapter<String>(search.this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.location1_4));
                        s4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        loc_detail.setAdapter(s4);
                    } else if (position == 5) {
                        ArrayAdapter s5 = new ArrayAdapter<String>(search.this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.location1_5));
                        s5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        loc_detail.setAdapter(s5);
                    } else if (position == 6) {
                        ArrayAdapter s6 = new ArrayAdapter<String>(search.this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.location1_6));
                        s6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        loc_detail.setAdapter(s6);
                    } else if (position == 7) {
                        ArrayAdapter s7 = new ArrayAdapter<String>(search.this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.location1_7));
                        s7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        loc_detail.setAdapter(s7);
                    } else if (position == 8) {
                        ArrayAdapter s8 = new ArrayAdapter<String>(search.this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.location1_8));
                        s8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        loc_detail.setAdapter(s8);
                    } else if (position == 9) {
                        ArrayAdapter s9 = new ArrayAdapter<String>(search.this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.location1_9));
                        s9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        loc_detail.setAdapter(s9);
                    }
                    loc_detail.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    } else if(menu == 2) {
            loc_detail.setVisibility(View.GONE);
            ArrayAdapter s = new ArrayAdapter<String>(search.this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.food));
            s.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            loc.setAdapter(s);
        }

        Button btnSearch = (Button)findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resources.gets.put("flag", false);
                if(menu == 1) {
                    if(TextUtils.isEmpty(searchTV.getText())){
                        if (loc.getSelectedItem().toString().equals("전체")) {
                            new NetworkSearch((Custom_Adapter) listView.getAdapter(), search.this).execute("?loc=전체");
                        } else {
                            new NetworkSearch((Custom_Adapter) listView.getAdapter(), search.this).execute("?loc=" + loc_detail.getSelectedItem().toString());
                        }
                    }else{
                        if (loc.getSelectedItem().toString().equals("전체")) {
                            new NetworkSearch((Custom_Adapter) listView.getAdapter(), search.this).execute("?loc=전체&search="+searchTV.getText());
                        } else {
                            new NetworkSearch((Custom_Adapter) listView.getAdapter(), search.this).execute("?loc=" + loc_detail.getSelectedItem().toString()+"&search="+searchTV.getText());
                        }
                    }
                }
                else if(menu == 2){
                    if(TextUtils.isEmpty(searchTV.getText())){
                            new NetworkSearch((Custom_Adapter) listView.getAdapter(), search.this).execute("?food_type="+loc.getSelectedItem().toString());
                    }else{
                            new NetworkSearch((Custom_Adapter) listView.getAdapter(), search.this).execute("?food_type="+loc.getSelectedItem().toString()+"&search="+searchTV.getText());
                    }
                }
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.view_sildebar, sidebar).commit();

        ImageButton side_menu = (ImageButton)findViewById(R.id.btn_sidebar7);
        side_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sidebar.showMenu();
            }
        });


        // 어댑터 만들기 -> 3요소 필요 (위치, 디자인, 데이터)
        adapter = new Custom_Adapter(search.this, //위치
                R.layout.adapter_search, // 디자인
                new ArrayList<ShopVo>()); // 데이터

        listView.setAdapter(adapter);

        new NetworkSearch((Custom_Adapter) listView.getAdapter(), search.this).execute(""); // 전체 불러오기

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

