package com.example.loginregistrtionformapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class home extends AppCompatActivity {

    TextView welcome, message, wl_fName, wl_lName, wl_gender, wl_email, wl_et_fName, wl_et_lName, wl_et_gender, wl_et_email;
    Button wl_btn_logout;
    SharedPreferences sp;

    private static final String KEY_EMAIL = "e_mail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcome = findViewById(R.id.welcome);
        message = findViewById(R.id.message);
        wl_fName = findViewById(R.id.wl_fName);
        wl_lName = findViewById(R.id.wl_lName);
        wl_gender = findViewById(R.id.wl_gender);
        wl_email = findViewById(R.id.wl_email);
        wl_et_fName = findViewById(R.id.wl_et_fName);
        wl_et_lName = findViewById(R.id.wl_et_lName);
        wl_et_gender = findViewById(R.id.wl_et_gender);
        wl_et_email = findViewById(R.id.wl_et_email);
        wl_btn_logout = findViewById(R.id.wl_btn_logout);

        sp = getSharedPreferences("LoginData", MODE_PRIVATE);

        String email = sp.getString(KEY_EMAIL, null);

        if(email != null){
            message.setText(email);
        }

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
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
                Intent i = new Intent(home.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}