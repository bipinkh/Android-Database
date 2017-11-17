package com.bipinkh.dbapp.ui.Base;

import android.os.Bundle;
import android.support.v4.util.LongSparseArray;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bipinkh.dbapp.dbapp;
import com.bipinkh.dbapp.injections.components.ApplicationComponent;

/**
 * Created by bipin on 11/16/2017.
 */

public class BaseActivity extends AppCompatActivity {
    private static ApplicationComponent mApplicationComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplicationComponent = dbapp.getApplicationComponent();
    }

    public ApplicationComponent applicationComponent(){
        return mApplicationComponent;
    }

}
