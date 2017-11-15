package com.bipinkh.dbapp.ui.Base;

/**
 * Created by bipin on 11/15/2017.
 */


public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}

