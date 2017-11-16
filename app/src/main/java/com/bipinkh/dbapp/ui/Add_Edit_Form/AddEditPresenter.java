package com.bipinkh.dbapp.ui.Add_Edit_Form;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bipinkh.dbapp.R;
import com.bipinkh.dbapp.models.database.User;
import com.bipinkh.dbapp.services.daoServices.UserDaoService;
import com.bipinkh.dbapp.ui.Base.BasePresenter;
import com.bipinkh.dbapp.ui.ListActivity.ListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

/**
 * Created by bipin on 11/15/2017.
 */

public class AddEditPresenter extends BasePresenter<AddEditMvpView> {

    UserDaoService userDaoService;

    public AddEditPresenter(){
        userDaoService = new UserDaoService();

    }

    @Override
    public void attachView(AddEditMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }



    public Boolean saveForm(Boolean editStatus, long edituserid, String pFirstName,
                            String pLastName, String pPhone, String pAddress, String pEmail, String pGender){
        Log.d("deb","saving form");
        Long user_id = null;

        if(pFirstName.length() == 0 || pLastName.length() == 0){
            Log.d("deb","First Name and Last name are compulsory");
            return Boolean.FALSE;
        }

        if (editStatus){
            userDaoService.deleteUser(edituserid);
            user_id=edituserid;
            Log.d("deb","Editing Form of id : "+String.valueOf(user_id));
        }

        Boolean saveStatus = userDaoService.
                saveUser(user_id, pFirstName, pLastName, Long.parseLong(pPhone), pAddress, pEmail,pGender);
        Log.d("deb","Form saved status :: "+saveStatus);
        return saveStatus;
    }

}
