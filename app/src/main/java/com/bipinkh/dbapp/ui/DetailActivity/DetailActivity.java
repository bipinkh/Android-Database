package com.bipinkh.dbapp.ui.DetailActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bipinkh.dbapp.R;
import com.bipinkh.dbapp.ui.ListActivity.ListActivity;
import com.bipinkh.dbapp.models.database.User;
import com.bipinkh.dbapp.functions.optionsMenu.edit_delete_menu;
import com.bipinkh.dbapp.services.daoServices.UserDaoService;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class DetailActivity extends AppCompatActivity implements DetailMvpView{

    Long userid;

    DetailPresenter mDetailPresenter;
    @BindView(R.id.img_ProfilePicture) ImageView mDisplayImage;
    @BindView(R.id.img_gear) ImageButton mGear;
    @BindViews({ R.id.text_Name, R.id.text_Email, R.id.text_Address,
            R.id.text_Phone, R.id.text_Gender  })
    List<TextView> mDisplayLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        mDetailPresenter = new DetailPresenter();
        mDetailPresenter.attachView(this);

        Intent mIntent = getIntent();
        userid = mIntent.getLongExtra("userid", -1);
        if (userid == -1){
            Bundle bundle = new Bundle();
            bundle.putString("message", "Cannot Open the detail of the user");
            Intent i = new Intent(DetailActivity.this,ListActivity.class).putExtras(bundle);
            i.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }else{
            displayContent(userid);
        }

        //check listener on more gear
        moreOptionsListeners();

    }

    private void moreOptionsListeners() {
        mGear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDetailPresenter.popUpMenu(v,userid,mGear);
            }
        });
    }

    //display contents
    private void displayContent(Long uid) {
        User user = mDetailPresenter.getUser(uid);
        mDisplayLists.get(0).setText(user.getFirst_name() + " " + user.getLast_name());
        mDisplayLists.get(1).setText("Email : "+ user.getEmail());
        mDisplayLists.get(3).setText("Phone : "+ String.valueOf(user.getPhone()));
        mDisplayLists.get(2).setText("Address : "+ user.getAddress());
        mDisplayLists.get(4).setText("Gender : " + user.getGender());
    }
}
