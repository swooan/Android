package com.example.final_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import res.my_info_VPAdapter;

public class my_Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_info);

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
}
