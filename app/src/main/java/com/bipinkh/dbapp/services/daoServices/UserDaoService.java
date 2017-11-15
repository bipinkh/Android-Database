package com.bipinkh.dbapp.services.daoServices;

import android.util.Log;

import com.bipinkh.dbapp.dbapp;
import com.bipinkh.dbapp.models.database.DaoSession;
import com.bipinkh.dbapp.models.database.User;
import com.bipinkh.dbapp.models.database.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bipin on 11/15/2017.
 */

public class UserDaoService {

    DaoSession daoSession;

    public UserDaoService() {
        daoSession = dbapp.getAppInstance().getDaoSession();
    }

    public List<User> getAllUsers(){
        List<User> userslist = new ArrayList<>();
        UserDao userDao = daoSession.getUserDao();
        try{
            userslist.addAll(userDao.loadAll());
        }catch (Exception e){
            Log.d("deb","Exception occured in getting users :: "+e.getMessage());
        }
        Log.d("deb","Got list of "+ userslist.size()+" users");

        return userslist;
    }

    public User getAUser(Long userid){
        User u = daoSession.getUserDao().load(userid);
        Log.d("deb","user returned : "+u.getEmail());
        return u;
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


    public Boolean saveUser(Long id, String fname, String lname,String phone,String address,String email, String gender){
        Log.d("deb",String.valueOf(id));
        UserDao userDao = daoSession.getUserDao();
        User u = new User(id,fname,lname,Long.parseLong(phone),address,email, gender);
        userDao.insert(u);
        Log.d("deb","new record insertion successful");
        return Boolean.TRUE;
    }


}
