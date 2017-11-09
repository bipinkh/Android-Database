package com.bipinkh.dbapp.activities.ListActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bipinkh.dbapp.R;
import com.bipinkh.dbapp.activities.DetailActivity.DetailActivity;
import com.bipinkh.dbapp.models.database.User;
import java.util.List;

import butterknife.ButterKnife;


/**
 * Created by bipin on 11/8/2017.
 */


public class UserListAdapter extends RecyclerView.Adapter <CustomViewHolder> {

    private List<User> userslist;

    //constructor of adapter to initialize the users list
    public UserListAdapter(List<User> userslist) {
        this.userslist = userslist;
    }

        @Override
    //called right when the adapter is created and is used to initialize the ViewHolder
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_row, parent, false);
            return new CustomViewHolder(itemView);

    }


    @Override
    //bind each view holder to adapter and pass the data
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final User user = userslist.get(position);
        holder.titlename.setText(user.getFirst_name()+" "+user.getLast_name());
        holder.email.setText(user.getEmail());
        holder.userid.setText(String.valueOf(user.getId()));

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
    }


    @Override
    public int getItemCount() {
        return userslist.size();
    }

}