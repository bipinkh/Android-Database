package com.bipinkh.dbapp.functions.database;

import android.util.Log;

import com.bipinkh.dbapp.dbapp;
import com.bipinkh.dbapp.models.database.DaoSession;
import com.bipinkh.dbapp.models.database.User;

/**
 * Created by bipin on 11/9/2017.
 */

public class deleteData {
    DaoSession daoSession;

    public deleteData(){
        daoSession = dbapp.getAppInstance().getDaoSession();
    }

    public Boolean deleteUser(Long id){
        Log.d("deb","deleting");
        try{
            User u = daoSession.getUserDao().load(id);
            daoSession.getUserDao().delete(u);
            Log.d("deb","deletion successful");
            return Boolean.TRUE;
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }
}

