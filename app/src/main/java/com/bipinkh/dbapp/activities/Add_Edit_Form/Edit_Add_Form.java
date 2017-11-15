package com.bipinkh.dbapp.activities.Add_Edit_Form;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bipinkh.dbapp.activities.ListActivity.ListActivity;
import com.bipinkh.dbapp.R;
import com.bipinkh.dbapp.models.database.User;
import com.bipinkh.dbapp.services.daoServices.UserDaoService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class Edit_Add_Form extends AppCompatActivity {
    Boolean isEditing;
    Long edituserid;

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

        //check if it's called for editing an user
        Intent i = getIntent();
        isEditing =i.getBooleanExtra("editing",Boolean.FALSE);
        edituserid =i.getLongExtra("userid",0);
        if (isEditing)
        {
            if (edituserid != 0)
            {   populateForm(edituserid); }
        }

    }

    private void populateForm(Long edituserid) {
        User u = new UserDaoService().getAUser(edituserid);
        first_name.setText(u.getFirst_name());
        last_name.setText(u.getLast_name());
        phone.setText(String.valueOf(u.getPhone()));
        email.setText(u.getEmail());
        gender.setPrompt(u.getGender());
        address.setText(u.getAddress());
    }

    //listeners
    @OnClick(R.id.savebutton)
    public void saveForm(){
        Log.d("deb","saving form");
        Long user_id = null;
        if(first_name.getText().toString().length() == 0 ||
                last_name.getText().toString().length() == 0){
            Toast.makeText(this,"First Name and Last name are compulsory", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEditing){
            new UserDaoService().deleteUser(edituserid);
            user_id=edituserid;
        }

        Boolean success = new UserDaoService().saveUser(
                            user_id,
                            first_name.getText().toString(),
                            last_name.getText().toString(),
                            phone.getText().toString(),
                            address.getText().toString(),
                            email.getText().toString(),
                            gender.getSelectedItem().toString()
                            );
        Log.d("deb","Form saved status :: "+success);

        Intent i = new Intent(this, ListActivity.class);
        i.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
