package com.bipinkh.dbapp.ui.ListActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bipinkh.dbapp.R;
import com.bipinkh.dbapp.ui.Add_Edit_Form.Edit_Add_Form;


public class ListActivity extends AppCompatActivity implements ListMvpView {

    ListPresenter mListPresenter;
    private RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_users);

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        mListPresenter = new ListPresenter();
        mListPresenter.attachView(this);
        mListPresenter.setUpUsersRecyclerView(recyclerView);
        mListPresenter.refreshUsersList();
        displayIfAnyInitialMessage();
    }


    private void displayIfAnyInitialMessage() {
        try{
            String createMessage = this.getIntent().getExtras().getString("message");
            if (createMessage.length() > 0){
                Toast.makeText(this,createMessage,Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
        }
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
        if ( mListPresenter.clickedAddToolbarButton(item))
        {
            Intent intent = new Intent(this, Edit_Add_Form.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mListPresenter.refreshUsersList();
    }

}


