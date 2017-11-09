package com.bipinkh.dbapp.activities.DetailActivity;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        //check listener on more gear
        moreOptions();

    }

    private void moreOptions() {
        final ImageView moreGear = (ImageView) findViewById(R.id.displayMoreGear);
        moreGear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_delete_menu.popup(v,userid,moreGear);
            }
        });
    }


    private void displayContent(Long uid) {
        User user = new getData().getAUser(uid);

        TextView displayName = (TextView) findViewById(R.id.displayName);
        TextView displayEmail = (TextView) findViewById(R.id.displayEmail);
        TextView displayPhone = (TextView) findViewById(R.id.displayPhone);
        TextView displayAddress = (TextView) findViewById(R.id.displayAddress);
        TextView displayGender = (TextView) findViewById(R.id.displayGender);

        displayName.setText(user.getFirst_name() + " " + user.getLast_name());
        displayEmail.setText("Email : "+ user.getEmail());
        displayPhone.setText("Phone : "+ String.valueOf(user.getPhone()));
        displayAddress.setText("Address : "+ user.getAddress());
        displayGender.setText("Gender : " + user.getGender());
    }
}
