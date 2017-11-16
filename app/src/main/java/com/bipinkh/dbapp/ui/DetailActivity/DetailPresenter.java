package com.bipinkh.dbapp.ui.DetailActivity;

import com.bipinkh.dbapp.models.database.User;
import com.bipinkh.dbapp.services.daoServices.UserDaoService;
import com.bipinkh.dbapp.ui.Base.BasePresenter;

/**
 * Created by bipin on 11/16/2017.
 */

public class DetailPresenter extends BasePresenter<DetailMvpView>{

    UserDaoService userDaoService;

    public DetailPresenter(){
        userDaoService = new UserDaoService();
    }

    @Override
    public void attachView(DetailMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    public User getUser(Long uid) {
        return userDaoService.getAUser(uid);
    }
}
