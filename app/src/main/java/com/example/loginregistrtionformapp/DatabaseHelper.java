package com.example.loginregistrtionformapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper{
    public DatabaseHelper(Context context) {
        super(context, "user_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table users(firstname Text, lastname Text, gender Varchar, email Text primary key, password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists users");
    }

    public boolean addUser(String firstname, String lastname, String gender, String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("gender", gender);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = myDB.insert("users",null, contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public ArrayList<Mclass> getUserData(String gender){
        SQLiteDatabase myDB = this.getReadableDatabase();
        ArrayList<Mclass> modelClassArrayList = new ArrayList<>();

        Cursor cursor = myDB.rawQuery("select * from users where gender like '" + gender + "'", null);
        if(cursor.getCount() != 0){
            cursor.moveToPosition(-1);

            while (cursor.moveToNext()){
                String user_fName = cursor.getString(0);
                String user_lName = cursor.getString(1);
                String user_gender = cursor.getString(2);
                String user_email = cursor.getString(3);

                modelClassArrayList.add(new Mclass(user_fName, user_lName, user_gender, user_email));
            }
            return modelClassArrayList;
        }
        else {
            return null;
        }
    }

    public boolean checkemail(String email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where email = ?", new String[] {email});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkUser(String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where email = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

}