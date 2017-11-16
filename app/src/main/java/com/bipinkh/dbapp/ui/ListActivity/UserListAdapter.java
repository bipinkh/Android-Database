package com.bipinkh.dbapp.ui.ListActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bipinkh.dbapp.R;
import com.bipinkh.dbapp.ui.Base.BasePresenter;
import com.bipinkh.dbapp.ui.DetailActivity.DetailActivity;
import com.bipinkh.dbapp.functions.optionsMenu.edit_delete_menu;
import com.bipinkh.dbapp.models.database.User;
import java.util.List;

import butterknife.OnClick;


/**
 * Created by bipin on 11/8/2017.
 */


public class UserListAdapter extends RecyclerView.Adapter <CustomViewHolder> {

    private List<User> userslist;
    Context parentContext;
    BasePresenter bPresenter;

    //constructor of adapter to initialize the users list
    public UserListAdapter(List<User> userslist) {
        this.userslist = userslist;
        bPresenter = new BasePresenter();
    }

        @Override
    //called right when the adapter is created and is used to initialize the ViewHolder
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parentContext = parent.getContext();
        View itemView = LayoutInflater.from(parentContext)
                .inflate(R.layout.user_list_row, parent, false);
            return new CustomViewHolder(itemView);

    }


    @Override
    //bind each view holder to adapter and pass the data
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        final User user = userslist.get(position);
        holder.mListName.setText(user.getFirst_name()+" "+user.getLast_name());
        holder.mListEmail.setText(user.getEmail());
        holder.mListId.setText(String.valueOf(user.getId()));

        //listener for each class
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Context c = v.getContext();
                Intent i = new Intent(c, DetailActivity.class);
                i.putExtra("userid",user.getId());
                c.startActivity(i);
                }
             });

        //listener on option menu
        holder.mListGear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView moreGear = (ImageView) holder.mListGear;
                bPresenter.popUpMenu(v, user.getId(), moreGear);
            }
        });
    }


    @Override
    public int getItemCount() {
        return userslist.size();
    }

}