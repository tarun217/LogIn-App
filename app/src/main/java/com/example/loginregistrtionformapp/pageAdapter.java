package com.example.loginregistrtionformapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class pageAdapter extends FragmentStateAdapter {

    public  pageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new FemaleFrag();
            case 2:
                return new UserFrag();
        }

        return new MaleFrag();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

