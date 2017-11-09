package com.bipinkh.dbapp.functions.database;

import android.media.Image;
import android.util.Log;

import com.bipinkh.dbapp.dbapp;
import com.bipinkh.dbapp.models.database.DaoSession;
import com.bipinkh.dbapp.models.database.User;
import com.bipinkh.dbapp.models.database.UserDao;

/**
 * Created by bipin on 11/8/2017.
 */

public class FormSave {
    DaoSession daoSession;

    public FormSave(){
        daoSession = dbapp.getAppInstance().getDaoSession();
    }


    public Boolean saveUser(String fname, String lname,String phone,String address,String email, String gender){
        UserDao userDao = daoSession.getUserDao();
        User u = new User(null,fname,lname,Long.parseLong(phone),address,email, gender);
        userDao.insert(u);
        Log.d("deb","new record insertion successful");
        return Boolean.TRUE;
    }


}
