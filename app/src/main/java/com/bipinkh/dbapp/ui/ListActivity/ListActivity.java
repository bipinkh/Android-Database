package com.bipinkh.dbapp.ui.ListActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bipinkh.dbapp.R;


public class ListActivity extends AppCompatActivity implements ListMvpView {

    ListPresenter mListPresenter;
    private RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mListPresenter = new ListPresenter(this);
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
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mListPresenter.toolbarButtonListener(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListPresenter.refreshUsersList();
    }

}


