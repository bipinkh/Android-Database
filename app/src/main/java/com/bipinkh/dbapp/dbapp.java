package com.bipinkh.dbapp;

import android.app.Application;

/**
 * Created by bipin on 11/8/2017.
 */


public class dbapp extends Application {

    private static dbapp appinstancce;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static dbapp getAppInstance()  {   return appinstancce; }

}
