package com.bipinkh.dbapp.activities.DetailActivity;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bipinkh.dbapp.R;
import com.bipinkh.dbapp.activities.ListActivity.ListActivity;
import com.bipinkh.dbapp.functions.database.getData;
import com.bipinkh.dbapp.models.database.User;
import com.bipinkh.dbapp.functions.optionsMenu.edit_delete_menu;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class DetailActivity extends AppCompatActivity {

    Long userid;
    @BindView(R.id.displayImage) ImageView displayImage;
    @BindView(R.id.displayName) TextView displayName;
    @BindView(R.id.displayEmail) TextView displayEmail;
    @BindView(R.id.displayAddress) TextView displayAddress;
    @BindView(R.id.displayPhone) TextView displayPhone;
    @BindView(R.id.displayGender) TextView displayGender;
    @BindView(R.id.displayMoreGear) ImageButton displayMoreGear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent mIntent = getIntent();
        userid = mIntent.getLongExtra("userid", -1);
        if (userid == -1){
            Intent i = new Intent(DetailActivity.this,ListActivity.class);
            i.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }else{
            displayContent(userid);
        }
        ButterKnife.bind(this);
        //check listener on more gear
        moreOptionsListeners();

    }

    //listener to option menu
    private void moreOptionsListeners() {
        displayGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_delete_menu.popup(v,userid,displayGender);
            }
        });
    }

    //display contents
    private void displayContent(Long uid) {
        User user = new getData().getAUser(uid);
        displayName.setText(user.getFirst_name() + " " + user.getLast_name());
        displayEmail.setText("Email : "+ user.getEmail());
        displayPhone.setText("Phone : "+ String.valueOf(user.getPhone()));
        displayAddress.setText("Address : "+ user.getAddress());
        displayGender.setText("Gender : " + user.getGender());
    }
}
