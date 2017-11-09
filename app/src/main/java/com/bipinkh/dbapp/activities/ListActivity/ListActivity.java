package com.bipinkh.dbapp.activities.ListActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bipinkh.dbapp.R;
import com.bipinkh.dbapp.activities.Add_Edit_Form.Edit_Add_Form;
import com.bipinkh.dbapp.models.database.User;
import com.bipinkh.dbapp.functions.database.getData;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class ListActivity extends AppCompatActivity {

    private UserListAdapter mAdapter;
    List<User> userslist = new ArrayList<>();
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("deb","activity start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //initialize variables
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_users);
        mAdapter = new UserListAdapter(userslist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        //set recycler view properties
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        refreshUsersList();
        ButterKnife.bind(this);
    }

    private void refreshUsersList() {
        //get all users list and display
        Log.d("deb","refreshing list");
        userslist.clear();
        userslist.addAll(new getData().getUsers());
        mAdapter.notifyDataSetChanged();
    }


    @OnClick(R.id.addbutton)
    public void proceedToAddForm(){
        Intent intent = new Intent(ListActivity.this, Edit_Add_Form.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshUsersList();
    }


}


