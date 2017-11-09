package com.bipinkh.dbapp.functions.database;

import android.util.Log;

import com.bipinkh.dbapp.dbapp;
import com.bipinkh.dbapp.models.database.DaoSession;
import com.bipinkh.dbapp.models.database.User;
import com.bipinkh.dbapp.models.database.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bipin on 11/8/2017.
 */

public class getData {

    DaoSession daoSession;

    public getData(){
        daoSession = dbapp.getAppInstance().getDaoSession();
    }

    public List<User> getUsers(){
        List<User> userslist = new ArrayList<>();
        UserDao userDao = daoSession.getUserDao();
        try{
            userslist.addAll(userDao.loadAll());
        }catch (Exception e){
            Log.d("deb","Exception occured in getting users :: "+e.getMessage());
        }
        Log.d("deb","sending all data of users");

        return userslist;
    }

    public User getAUser(Long userid){
        User u = daoSession.getUserDao().load(userid);
        return u;
    }
}
