package com.bipinkh.dbapp;

import android.app.Application;
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
        Log.d("deb","opening database");
        try {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "users_db.db");
        Log.d("deb","database helper ready");
        Database db = helper.getWritableDb();
        Log.d("deb","getting session");
        daoSession = new DaoMaster(db).newSession();
        Log.d("deb","got daoSession");
        }catch(Exception e){
            Log.d("deb",e.getMessage());
        }
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
