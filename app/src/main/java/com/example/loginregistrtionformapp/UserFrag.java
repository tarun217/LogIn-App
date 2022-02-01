package com.example.loginregistrtionformapp;


import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserFrag extends Fragment {

    TextView welcome, message, wl_fName, wl_lName, wl_gender, wl_email, wl_et_fName, wl_et_lName, wl_et_gender, wl_et_email;
    Button wl_btn_logout;
    ImageView wl_image;
    SharedPreferences sp;

    private static final String KEY_EMAIL = "e_mail";

    public UserFrag() {
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
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        message = v.findViewById(R.id.message);
        welcome = v.findViewById(R.id.welcome);
        wl_fName = v.findViewById(R.id.wl_fName);
        wl_lName = v.findViewById(R.id.wl_lName);
        wl_gender = v.findViewById(R.id.wl_gender);
        wl_email = v.findViewById(R.id.wl_email);
        wl_et_fName = v.findViewById(R.id.wl_et_fName);
        wl_et_lName = v.findViewById(R.id.wl_et_lName);
        wl_et_gender = v.findViewById(R.id.wl_et_gender);
        wl_et_email = v.findViewById(R.id.wl_et_email);
        wl_btn_logout = v.findViewById(R.id.wl_btn_logout);

        SharedPreferences sp = getContext().getSharedPreferences("LoginData", MODE_PRIVATE);

        String email = sp.getString(KEY_EMAIL, null);

        if(email != null){
            message.setText(email);
        }

        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor userData = db.rawQuery("select * from users where email LIKE '" + email + "'", null);
        userData.moveToFirst();
        do{
            wl_et_fName.setText(userData.getString(0));
            wl_et_lName.setText(userData.getString(1));
            wl_et_gender.setText(userData.getString(2));
            wl_et_email.setText(userData.getString(3));
        }
        while(userData.moveToNext());

        db.close();

        wl_btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });

        return v;
    }
}