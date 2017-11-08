package com.bipinkh.dbapp;

import android.app.Application;

import com.bipinkh.dbapp.models.database.DaoMaster;
import com.bipinkh.dbapp.models.database.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by bipin on 11/8/2017.
 */


public class dbapp extends Application {

    private static dbapp appinstancce;
    DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDaoSession();
    }

    public void initializeDaoSession(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"users-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static dbapp getAppInstance()  {   return appinstancce;  }

    public DaoSession getDaoSession(){
        if (daoSession==null)   {   initializeDaoSession();   }
        return daoSession;
    }

}
