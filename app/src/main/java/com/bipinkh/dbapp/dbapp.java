package com.bipinkh.dbapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.bipinkh.dbapp.models.database.DaoMaster;
import com.bipinkh.dbapp.models.database.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by bipin on 11/8/2017.
 */


public class dbapp extends Application {

    private static dbapp appinstance;
    static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        appinstance=this;
        initializeDaoSession();
    }

    //initialize daosession
    public void initializeDaoSession(){
        Log.d("deb","getting daoSession");
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"users-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    //get app instance
    public static dbapp getAppInstance()  {   return appinstance;  }

    //get dao instance
    public DaoSession getDaoSession(){
        if (daoSession==null) {
            initializeDaoSession();
        }
        Log.d("deb","sending daosession");
        return daoSession;
    }

}
