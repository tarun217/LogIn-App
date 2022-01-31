package com.example.loginregistrtionformapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText mEmail;
    EditText mTextPassword;
    Button mButtonLogin;
    Button mButtonRegister;
    RadioButton fmale,male;
    SharedPreferences sp;

    private static final String KEY_EMAIL = "e_mail";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDb = new DatabaseHelper(this);

        mEmail = (EditText) findViewById(R.id.edittext_email);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mButtonRegister = (Button) findViewById(R.id.button_register);

        sp = getSharedPreferences("LoginData", MODE_PRIVATE);

        String email = sp.getString(KEY_EMAIL, null);
        if(email != null){
            Intent intent = new Intent(MainActivity.this, home.class);
            startActivity(intent);
        }
        myDb = new DatabaseHelper(this);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, Register.class);

                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_id = mEmail.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                if(email_id.equals("") || pwd.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter the credentials", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean loginResult = myDb.checkUser(email_id,pwd);
                    if(loginResult == true){
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(KEY_EMAIL,mEmail.getText().toString());
                        editor.apply();


                        Intent intent = new Intent(MainActivity.this, home.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }


}






