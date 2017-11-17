package com.bipinkh.dbapp.injections.components;

import com.bipinkh.dbapp.injections.modules.ApplicationModule;
import com.bipinkh.dbapp.services.daoServices.UserDaoService;
import com.bipinkh.dbapp.ui.ListActivity.ListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by bipin on 11/17/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(ListActivity listActivity);
}
