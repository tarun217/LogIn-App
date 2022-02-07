package com.example.loginregistrtionformapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MaleFrag extends Fragment {

    RecyclerView maleUsers_RecyclerView;
    UserlistAdapter adapter;

    public MaleFrag() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_male, container, false);

        maleUsers_RecyclerView = v.findViewById(R.id.maleRv);

        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        adapter = new UserlistAdapter(dbHelper.getUserData("Male"),getContext());

        maleUsers_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        maleUsers_RecyclerView.setAdapter(adapter);

        return v;
    }
}




