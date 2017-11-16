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

public class Edit_Add_Form extends AppCompatActivity implements AddEditMvpView{

    @BindView(R.id.inp_FirstName) EditText mFirstName;
    @BindView(R.id.inp_LastName) EditText mLastName;
    @BindView(R.id.inp_Phone) EditText mPhone;
    @BindView(R.id.inp_Address) EditText mAddress;
    @BindView(R.id.inp_Email) EditText mEmail;
    @BindView(R.id.inp_Gender) Spinner mGender;
    @BindView(R.id.inp_Image) ImageView mImage;

    AddEditPresenter mAddEditPresenter;
    Boolean isEditing;
    Long edituserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__add__form);
        mAddEditPresenter = new AddEditPresenter(this);
        mAddEditPresenter.attachView(this);
        ButterKnife.bind(this);

        //check if it's called for editing an user or insertion of new user
        initializeForm();

    }

    private void initializeForm() {
        Intent i = getIntent();
        isEditing =i.getBooleanExtra("editing",Boolean.FALSE);
        edituserid =i.getLongExtra("userid",0);
            if (isEditing)
            {
                if (edituserid != 0)
                {   populateForm(edituserid); }
            }

        }


    @Override
    protected void onDestroy() {
        mAddEditPresenter.detachView();
        super.onDestroy();
    }

    //listeners
    @OnClick(R.id.btn_Save)
    public void saveForm(){
        Log.d("deb","save form command received");
        Boolean success =
                mAddEditPresenter.saveForm(
                                    isEditing,
                                    edituserid,
                                    mFirstName.getText().toString(),
                                    mLastName.getText().toString(),
                                    mPhone.getText().toString(),
                                    mAddress.getText().toString(),
                                    mEmail.getText().toString(),
                                    mGender.getSelectedItem().toString()
                                    );
        if (success) {
            Bundle bundle = new Bundle();
            bundle.putString("message", "Form Save Successful");
            Intent i = new Intent(this, ListActivity.class).putExtras(bundle);
            i.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }else{
            Toast.makeText(this,"Form cannot be saved. " +
                    "Make sure to enter values for fields", Toast.LENGTH_SHORT).show();
        }
    }

    public void populateForm(long edituserid){
        User u = new UserDaoService().getAUser(edituserid);
        mFirstName.setText(u.getFirst_name());
        mLastName.setText(u.getLast_name());
        mPhone.setText(String.valueOf(u.getPhone()));
        mEmail.setText(u.getEmail());
        mGender.setPrompt(u.getGender());
        mAddress.setText(u.getAddress());
    }
}
