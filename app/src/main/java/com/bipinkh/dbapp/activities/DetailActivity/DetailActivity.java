package com.bipinkh.dbapp.activities.DetailActivity;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bipinkh.dbapp.R;
import com.bipinkh.dbapp.activities.ListActivity.ListActivity;
import com.bipinkh.dbapp.functions.database.getData;
import com.bipinkh.dbapp.models.database.User;
import com.bipinkh.dbapp.functions.optionsMenu.edit_delete_menu;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class DetailActivity extends AppCompatActivity {

    Long userid;

    @BindView(R.id.displayImage) ImageView displayImage;
    @BindView(R.id.displayMoreGear) ImageButton displayMoreGear;
    @BindViews({ R.id.displayName, R.id.displayEmail, R.id.displayAddress,
            R.id.displayPhone, R.id.displayGender  })
    List<TextView> displayLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

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
        moreOptionsListeners();

    }

    //listener to option menu
    private void moreOptionsListeners() {
//        final TextView displayGender = (TextView) findViewById(R.id.displayGender);
        displayMoreGear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_delete_menu.popup(v,userid,displayMoreGear);
            }
        });
    }

    //display contents
    private void displayContent(Long uid) {
        User user = new getData().getAUser(uid);
        displayLists.get(0).setText(user.getFirst_name() + " " + user.getLast_name());
        displayLists.get(1).setText("Email : "+ user.getEmail());
        displayLists.get(3).setText("Phone : "+ String.valueOf(user.getPhone()));
        displayLists.get(2).setText("Address : "+ user.getAddress());
        displayLists.get(4).setText("Gender : " + user.getGender());
    }
}
