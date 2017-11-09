package com.bipinkh.dbapp.activities.Add_Edit_Form;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bipinkh.dbapp.functions.database.*;
import com.bipinkh.dbapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class Edit_Add_Form extends AppCompatActivity {


    @BindView(R.id.first_name) EditText first_name;
    @BindView(R.id.last_name) EditText last_name;
    @BindView(R.id.phone) EditText phone;
    @BindView(R.id.address) EditText address;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.gender) Spinner gender;
    @BindView(R.id.topimage) ImageView topimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__add__form);
        ButterKnife.bind(this);
    }

    //listeners
    @OnClick(R.id.savebutton)
    public void saveForm(){
        Log.d("deb","saving form");
        Boolean success = new FormSave().saveUser(
                            first_name.getText().toString(),
                            last_name.getText().toString(),
                            phone.getText().toString(),
                            address.getText().toString(),
                            email.getText().toString(),
                            gender.getSelectedItem().toString()
                            );
        Log.d("deb","Form saved status :: "+success);
        Intent returnIntent = new Intent();
        if(success){
            setResult(Activity.RESULT_OK,returnIntent);
        }else{
            setResult(Activity.RESULT_CANCELED,returnIntent);
        }
        finish();
    }
}
