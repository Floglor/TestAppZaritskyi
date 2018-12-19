package com.example.floggy.testappzaritskyi;

import android.app.Application;

import com.example.floggy.testappzaritskyi.presenters.APIManager;

public class MainApplication extends Application {
    public static APIManager apiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        apiManager = APIManager.getInstance();
    }

}
