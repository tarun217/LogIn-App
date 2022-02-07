package com.example.loginregistrtionformapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FemaleFrag extends Fragment {


    RecyclerView femaleUser_RecyclerView;
    UserlistAdapter adapter;

    public FemaleFrag() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_female, container, false);
        femaleUser_RecyclerView = v.findViewById(R.id.femaleRv);

        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        adapter = new UserlistAdapter(dbHelper.getUserData("Female"), getContext());

        femaleUser_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        femaleUser_RecyclerView.setAdapter(adapter);

        return v;
    }
}