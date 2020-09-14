package com.example.final_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.tabs.TabLayout;


import res.detail_VPAdapter;

public class shop_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_detail);

        Intent intent = new Intent(this, Loading.class);
        startActivity(intent);

        TabLayout tabLayout = findViewById(R.id.detail_tab);

        tabLayout.addTab(tabLayout.newTab().setText("가게 정보"));
        tabLayout.addTab(tabLayout.newTab().setText("예약 정보"));
        tabLayout.addTab(tabLayout.newTab().setText("리 뷰"));

        tabLayout.setTabGravity((TabLayout.GRAVITY_FILL));

        final ViewPager viewPager = findViewById(R.id.viewpager);
        final detail_VPAdapter adapter = new detail_VPAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
