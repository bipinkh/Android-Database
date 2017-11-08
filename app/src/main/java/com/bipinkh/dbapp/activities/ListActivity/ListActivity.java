package com.bipinkh.dbapp.activities.ListActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bipinkh.dbapp.R;
import com.bipinkh.dbapp.dbapp;
import com.bipinkh.dbapp.models.database.DaoSession;
import com.bipinkh.dbapp.models.database.User;
import com.bipinkh.dbapp.models.database.UserDao;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private UserListAdapter mAdapter;
    DaoSession daoSession;
    List<User> userslist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("deb","activity start");
        super.onCreate(savedInstanceState);
        Log.d("deb","starting");
        setContentView(R.layout.activity_list);
        Log.d("deb","started");
        Toast.makeText(this, "Check", Toast.LENGTH_SHORT).show();

        //get daoSession
        daoSession = dbapp.getAppInstance().getDaoSession();
        //initialize variables
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new UserListAdapter(userslist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        //set recycler view properties
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        Log.d("deb","fetching lists");
        fetchUsersList();

    }

    private void fetchUsersList() {
        userslist.clear();
        Log.d("deb","getting database");
        UserDao userDao = daoSession.getUserDao();
        Log.d("deb","getting users");
        try{
            userslist.addAll(userDao.loadAll());
        }catch (Exception e){
            Log.d("deb","Exception occured in getting users :: "+e.getMessage());
        }
        mAdapter.notifyDataSetChanged();
    }

}
