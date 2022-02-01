package com.example.loginregistrtionformapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;

public class HomePage extends AppCompatActivity {

    TabLayout tab_layout;
    ViewPager2 pager2;
    pageAdapter adapter;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);




        tab_layout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.vpager);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new pageAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);



        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tab_layout.selectTab(tab_layout.getTabAt(position));
            }
        });



    }
}