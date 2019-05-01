package com.example.a2019.ecomerceapp;

import android.app.Application;

import com.example.a2019.ecomerceapp.Admin.RoomDataBaseUtilite.MyDatabase;

public class HGroupApplicationClass extends Application  {
    @Override
    public void onCreate() {
        super.onCreate();
        MyDatabase.init(this);
        //.........
    }
}
