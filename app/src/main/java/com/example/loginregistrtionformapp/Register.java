package com.example.loginregistrtionformapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import  android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;


public class Register extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText Fname;
    EditText Lname;
    EditText memail;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    Button mButtonLogin;
    TextView _gender;
    String gender_ = "";
    RadioGroup genderGrp;
    RadioButton Female, Male;


    private Boolean validatefName(){
        String val =  Fname.getText().toString();
        String noWhiteSpace = "\\A\\w{2,20}\\z";

        if(val.isEmpty()){
            Fname.setError("First Name cannot be empty");
            return false;
        }

        else if(!val.matches(noWhiteSpace)){
            Fname.setError("White spaces are not allowed");
            return false;
        }

        else{
            Fname.setError(null);
            return true;
        }
    }
    private Boolean validatelName(){
        String val =  Lname.getText().toString();
        String noWhiteSpace = "\\A\\w{2,20}\\z";

        if(val.isEmpty()){
            Lname.setError("Last Name cannot be empty");
            return false;
        }

        else if(!val.matches(noWhiteSpace)){
            Lname.setError("White spaces are not allowed");
            return false;
        }

        else{
            Lname.setError(null);
            return true;
        }
    }
    private Boolean validateEmail(){
        String val =  memail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            memail.setError("Email cannot be empty");
            return false;
        }

        else if (!val.matches(emailPattern)) {
            memail.setError("Invalid email address");
            return false;
        }

        else{
            memail.setError(null);
            return true;
        }
    }


    private Boolean validatePwd(){
        String val =  mTextPassword.getText().toString();
        String passwordVal = "^" +
                "(?=.*[0-9])" +
                "(?=.*[a-zA-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
                ".{8,}" +
                "$";

        if(val.isEmpty()){
            mTextCnfPassword.setError("Password cannot be empty");
            return false;
        }

        else if (!val.matches(passwordVal)) {
            mTextPassword.setError("Invalid password");
            return false;
        }

        else{
            mTextPassword.setError(null);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDb = new DatabaseHelper(this);
        Fname =(EditText)findViewById(R.id.fname);
        Lname =(EditText)findViewById(R.id.lname);
        memail = (EditText)findViewById(R.id.edittext_email);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        genderGrp = findViewById(R.id.radioGroup);
        _gender= findViewById(R.id.genderG);
        Male = findViewById(R.id.radiobutton_male);
        Female = findViewById(R.id.radiobutton_female);


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences("MYPREFS",MODE_PRIVATE);
                Intent LoginIntent = new Intent(Register.this,MainActivity.class);
                startActivity(LoginIntent);

            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validatefName() |!validatelName() | !validatePwd() | !validateEmail()){
                    return;
                }
                String fname = Fname.getText().toString().trim();
                String lname = Lname.getText().toString().trim();
                String email = memail.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();
                String gnder = gender_;


                if(fname.equals("") || lname.equals("") ||email.equals("") || pwd.equals("") || cnf_pwd.equals(pwd)){
                    Toast.makeText(Register.this, "Fill all the fields!", Toast.LENGTH_SHORT).show();
                }

                else{
                    Boolean emailCheckResult =  myDb.checkemail(email);
                    if(emailCheckResult == false){
                        Boolean regResult = myDb.addUser(fname,lname,gnder,email,pwd);
                        if(regResult == true){
                            Toast.makeText(Register.this, "Registration done successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent( Register.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Register.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Register.this, "User already exists.\n Please LOGIN!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
     genderGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(RadioGroup group, int i) {
          if(i==R.id.radiobutton_female){
              gender_="Female";
          }else if(i==R.id.radiobutton_male){
              gender_="Male";
          }

         }
     });



    }

}
