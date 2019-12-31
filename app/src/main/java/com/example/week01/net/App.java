package com.example.week01.net;

import android.app.Application;
import android.content.Context;
//创建App
public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
