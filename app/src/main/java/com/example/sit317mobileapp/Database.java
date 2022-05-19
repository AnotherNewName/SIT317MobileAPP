package com.example.sit317mobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context, "Mobile_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//create the database tabless
        String CREATE_LOGIN_TABLE= "CREATE TABLE LOGIN(LOGINID INTEGER PRIMARY KEY AUTOINCREMENT,EMAIL TEXT, USERNAME TEXT, PASSWORD TEXT, FULL_NAME TEXT)";
        String CREATE_YOUTUBE_TABLE = "CREATE TABLE YOUTUBE(URL TEXT PRIMARY KEY)";
        String CREATE_CHAT_LOGS_TABLE = "CREATE TABLE CHAT_LOGS(MESSAGE_ID INTEGER PRIMARY KEY AUTOINCREMENT, PERSON TEXT, MESSAGE TEXT)";
        sqLiteDatabase.execSQL(CREATE_LOGIN_TABLE);
        sqLiteDatabase.execSQL(CREATE_YOUTUBE_TABLE);
        sqLiteDatabase.execSQL(CREATE_CHAT_LOGS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {//reset the database tables
        String DROP_LOGIN_TABLE = "DROP TABLE IF EXISTS LOGIN";
        String DELETE_YOUTUBE_TABLE = "DROP TABLE IF EXISTS YOUTUBE";
        String DELETE_CHAT_LOGS_TABLE = "DROP TABLE IF EXISTS CHAT_LOGS";
        sqLiteDatabase.execSQL(DROP_LOGIN_TABLE);
        sqLiteDatabase.execSQL(DELETE_YOUTUBE_TABLE);
        sqLiteDatabase.execSQL(DELETE_CHAT_LOGS_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long InsertYoutubeURL(String URL){//insert a youtube URL into the youtube table
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues VALUES = new ContentValues();
        VALUES.put("URL",URL);
        long row = db.insert("YOUTUBE", null, VALUES);
        db.close();
        return row;
    }

    public void DeleteYoutubeURL(String URL){//delete a youtube url from the youtube table
        SQLiteDatabase db = this.getWritableDatabase();
        String WhereClause = "URL=?";
        String[] WhereArgs = {String.valueOf(URL)};
        db.delete("YOUTUBE", WhereClause, WhereArgs);
        db.close();
    }

    public ArrayList<String> FetchAllYoutubeURL(){//fetch all URLs from the youtube table and return as an arraylist
        ArrayList<String> URLList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String SELECT_ALL_URLS = "SELECT * FROM YOUTUBE";
        Cursor cursor = db.rawQuery(SELECT_ALL_URLS, null);
        if (cursor.moveToFirst()){
            do {
                String temp = cursor.getString(0);
                URLList.add(temp);
            }while (cursor.moveToNext());
        }
        return URLList;
    }

    public long InsertLog(Logs Log){// insert a chat log into the chat logs table
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues VALUES = new ContentValues();
        VALUES.put("PERSON",Log.Person);
        VALUES.put("MESSAGE",Log.Message);
        long row = db.insert("CHAT_LOGS", null, VALUES);
        db.close();
        return row;
    }

    public ArrayList<Logs> FetchAllLogs(){//fetch all the chat logs from the chat logs table and return as a n arraylist
        ArrayList<Logs> LogsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_ALL_CHAT_LOGS = "SELECT * FROM CHAT_LOGS";
        Cursor cursor = db.rawQuery(SELECT_ALL_CHAT_LOGS, null);
        if (cursor.moveToFirst()){
            do {
                Logs temp = new Logs(cursor.getString(1), cursor.getString(2));
                LogsList.add(temp);
            }while (cursor.moveToNext());
        }
        return LogsList;
    }

    public long InsertLogin(Login account){//insert a new account into the login table
        //create a connection to the database
        SQLiteDatabase db = this.getWritableDatabase();
        //create the query to insert the login data
        ContentValues Values = new ContentValues();
        Values.put("EMAIL", account.getEmail());
        Values.put("USERNAME", account.getUsername());
        Values.put("PASSWORD", account.getPassword());
        Values.put("FULL_NAME", account.getFull_Name());
        //execute the query
        long row = db.insert("LOGIN",null, Values);
        //close the connection
        db.close();
        return row;
    }

    public ArrayList<Login> FetchAllLogins() {//fetch all the logins from the login table and return as arraylist
        //create an arraylist to store the login data
        ArrayList<Login> LoginList = new ArrayList<>();
        //create a connection to the database
        SQLiteDatabase db = this.getReadableDatabase();
        //create the query to get the login data from the database
        String SELECT_ALL_LOGIN = "SELECT * FROM LOGIN";
        //execute the query
        Cursor cursor = db.rawQuery(SELECT_ALL_LOGIN, null);
        if (cursor.moveToFirst()) {
            do {
                //move the login data into a temporary login variable
                Login temp = new Login(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                //add the login variable to the arraylist
                LoginList.add(temp);
            } while (cursor.moveToNext());
        }
        //close the connection
        db.close();
        return LoginList;
    }
    public void ResetTables(){//reset all the tables for testing
        SQLiteDatabase db = this.getReadableDatabase();
        String DROP_LOGIN_TABLE = "DROP TABLE IF EXISTS LOGIN";
        String DELETE_YOUTUBE_TABLE = "DROP TABLE IF EXISTS YOUTUBE";
        String DELETE_CHAT_LOGS_TABLE = "DROP TABLE IF EXISTS CHAT_LOGS";
        db.execSQL(DROP_LOGIN_TABLE);
        db.execSQL(DELETE_YOUTUBE_TABLE);
        db.execSQL(DELETE_CHAT_LOGS_TABLE);
        onCreate(db);
        db.close();
    }
}
