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

public class ListActivity extends AppCompatActivity implements ListMvpView {

    ListPresenter mListPresenter;
    private RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mListPresenter = new ListPresenter();
        mListPresenter.attachView(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_users);

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        mListPresenter.setUpUsersRecyclerView(recyclerView);
        mListPresenter.refreshUsersList();
    }


    @Override
    protected void onDestroy() {
        mListPresenter.detachView();
        super.onDestroy();
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

    @Override
    protected void onResume() {
        super.onResume();
        mListPresenter.refreshUsersList();
    }

}


