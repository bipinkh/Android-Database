package com.bipinkh.dbapp.ui.Add_Edit_Form;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bipinkh.dbapp.ui.ListActivity.ListActivity;
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

    @BindView(R.id.inp_FirstName) EditText mFirstName;
    @BindView(R.id.inp_LastName) EditText mLastName;
    @BindView(R.id.inp_Phone) EditText mPhone;
    @BindView(R.id.inp_Address) EditText mAddress;
    @BindView(R.id.inp_Email) EditText mEmail;
    @BindView(R.id.inp_Gender) Spinner mGender;
    @BindView(R.id.inp_Image) ImageView mImage;

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
        mFirstName.setText(u.getFirst_name());
        mLastName.setText(u.getLast_name());
        mPhone.setText(String.valueOf(u.getPhone()));
        mEmail.setText(u.getEmail());
        mGender.setPrompt(u.getGender());
        mAddress.setText(u.getAddress());
    }

    //listeners
    @OnClick(R.id.btn_Save)
    public void saveForm(){
        Log.d("deb","saving form");
        Long user_id = null;
        if(mFirstName.getText().toString().length() == 0 ||
                mLastName.getText().toString().length() == 0){
            Toast.makeText(this,"First Name and Last name are compulsory", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEditing){
            new UserDaoService().deleteUser(edituserid);
            user_id=edituserid;
        }

        Boolean success = new UserDaoService().saveUser(
                            user_id,
                            mFirstName.getText().toString(),
                            mLastName.getText().toString(),
                            mPhone.getText().toString(),
                            mAddress.getText().toString(),
                            mEmail.getText().toString(),
                            mGender.getSelectedItem().toString()
                            );
        Log.d("deb","Form saved status :: "+success);

        Intent i = new Intent(this, ListActivity.class);
        i.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
