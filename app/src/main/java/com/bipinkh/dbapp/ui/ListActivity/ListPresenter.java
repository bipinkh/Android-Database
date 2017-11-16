package com.bipinkh.dbapp.ui.ListActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.bipinkh.dbapp.R;
import com.bipinkh.dbapp.dbapp;
import com.bipinkh.dbapp.models.database.User;
import com.bipinkh.dbapp.services.daoServices.UserDaoService;
import com.bipinkh.dbapp.ui.Add_Edit_Form.Edit_Add_Form;
import com.bipinkh.dbapp.ui.Base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bipin on 11/15/2017.
 */

public class ListPresenter extends BasePresenter<ListMvpView> {
    private UserListAdapter mAdapter;
    List<User> userslist = new ArrayList<>();

    private Context ListActivityContext;

    public ListPresenter(Context context){
        ListActivityContext = context;
    }

    @Override
    public void attachView(ListMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void setUpUsersRecyclerView(RecyclerView recyclerView) {
        Log.d("deb","setting up recycler view");
        //initialize variables
        mAdapter = new UserListAdapter(userslist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(dbapp.getAppContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public void refreshUsersList() {
        Log.d("deb","refreshing users list");
        //get all users list and display
        userslist.clear();
        userslist.addAll(new UserDaoService().getAllUsers());
        mAdapter.notifyDataSetChanged();
    }

//add button of toolbar
    public void toolbarButtonListener(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_toolbar_button) {
            Intent intent = new Intent(ListActivityContext, Edit_Add_Form.class);
            ListActivityContext.startActivity(intent);
        }
    }
}
