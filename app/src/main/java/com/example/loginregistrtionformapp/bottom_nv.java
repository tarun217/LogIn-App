package com.example.loginregistrtionformapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class bottom_nv extends AppCompatActivity {

    BottomNavigationView btm_nav;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nv);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().add(R.id.frame, new MaleFrag()).commit();

        btm_nav = findViewById(R.id.btm_nav);

        btm_nav.setSelectedItemId(R.id.btm_nav_maleusers);
        btm_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId()){

                    case R.id.btm_nav_maleusers:
                        selectedFragment = new MaleFrag();
                        break;

                    case R.id.btm_nav_femaleusers:
                        selectedFragment = new MaleFrag();
                        break;

                    case R.id.btm_nav_useracc:
                        selectedFragment = new UserFrag();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame, selectedFragment).commit();

                return true;
            }
        });
    }
}