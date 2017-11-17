package com.bipinkh.dbapp.injections.modules;

import android.util.Log;

import com.bipinkh.dbapp.dbapp;
import com.bipinkh.dbapp.models.database.DaoSession;
import com.bipinkh.dbapp.services.daoServices.UserDaoService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bipin on 11/17/2017.
 */


@Module
public class ApplicationModule {

    @Provides
    @Singleton
    public UserDaoService provideUserDaoService(){
        Log.d("deb","getting user dao service");
        return new UserDaoService();
    }

}
