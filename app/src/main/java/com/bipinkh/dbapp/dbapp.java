package com.bipinkh.dbapp;

import android.app.Application;
import android.util.Log;

import com.bipinkh.dbapp.injections.components.DaggerApplicationComponent;
import com.bipinkh.dbapp.injections.components.ApplicationComponent;
import com.bipinkh.dbapp.injections.modules.ApplicationModule;
import com.bipinkh.dbapp.models.database.DaoMaster;
import com.bipinkh.dbapp.models.database.DaoSession;

import org.greenrobot.greendao.database.Database;

import timber.log.Timber;

/**
 * Created by bipin on 11/8/2017.
 */


public class dbapp extends Application {

    private static dbapp appinstance;
    static DaoSession daoSession;
    private static ApplicationComponent applicationComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();
        appinstance=this;
        initializeDaoSession();
        //plant timber
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static ApplicationComponent getApplicationComponent(){
        if (applicationComponent == null){
            ApplicationModule applicationModule = new ApplicationModule();
            Log.d("deb","generating new application component");
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule())
                    .build();
        }
        return applicationComponent;
    }


    //initialize daosession
    public void initializeDaoSession(){
        Log.d("deb","opening database");
        try {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "users_db.db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        }catch(Exception e){
            Log.d("deb",e.getMessage());
        }
        Log.d("deb","initiating launcher activity");

    }

    //get app instance
    public static dbapp getAppInstance()  {   return appinstance;  }

    public static dbapp getAppContext()  {   return appinstance;  }

    //get dao instance
    public DaoSession getDaoSession(){
        if (daoSession==null) {
            initializeDaoSession();
        }
        Log.d("deb","sending daosession");
        return daoSession;
    }

}
