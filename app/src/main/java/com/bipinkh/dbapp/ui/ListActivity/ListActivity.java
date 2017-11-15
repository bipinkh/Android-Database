package com.bipinkh.dbapp.ui.ListActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bipinkh.dbapp.R;
import com.bipinkh.dbapp.ui.Add_Edit_Form.Edit_Add_Form;
import com.bipinkh.dbapp.models.database.User;
import com.bipinkh.dbapp.services.daoServices.UserDaoService;

import java.util.ArrayList;
import java.util.List;



public class ListActivity extends AppCompatActivity {

    private UserListAdapter mAdapter;
    List<User> userslist = new ArrayList<>();
    private RecyclerView recyclerView;


    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("deb","activity start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //initialize variables
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_users);
        mAdapter = new UserListAdapter(userslist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        Log.d("deb","setting up recycler view");
        //set recycler view properties
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        Log.d("deb","set up recycler view");

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        refreshUsersList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_toolbar_button) {
            Intent intent = new Intent(ListActivity.this, Edit_Add_Form.class);
            startActivity(intent);
            return true;
        }
       return super.onOptionsItemSelected(item);
    }


    private void refreshUsersList() {
        //get all users list and display
        Log.d("deb","refreshing list");
        userslist.clear();
        userslist.addAll(new UserDaoService().getAllUsers());
        mAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        super.onResume();
        refreshUsersList();
    }

}

