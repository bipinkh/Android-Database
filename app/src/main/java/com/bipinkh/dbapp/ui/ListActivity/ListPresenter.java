package com.bipinkh.dbapp.ui.ListActivity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bipinkh.dbapp.R;
import com.bipinkh.dbapp.dbapp;
import com.bipinkh.dbapp.models.database.User;
import com.bipinkh.dbapp.services.daoServices.UserDaoService;
import com.bipinkh.dbapp.ui.Base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bipin on 11/15/2017.
 */

public class ListPresenter extends BasePresenter<ListMvpView> {
    private UserListAdapter mAdapter;
    List<User> userslist = new ArrayList<>();

    public ListPresenter(){
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
        //initialize variables
        mAdapter = new UserListAdapter(userslist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(dbapp.getAppContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public void refreshUsersList() {
        //get all users list and display
        userslist.clear();
        userslist.addAll(new UserDaoService().getAllUsers());
        mAdapter.notifyDataSetChanged();
    }


}
