package com.bipinkh.dbapp.activities.ListActivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bipinkh.dbapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bipin on 11/8/2017.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView titlename, email, userid;
        public ImageButton moreGear;

        public CustomViewHolder(final View view) {
            super(view);
            titlename = (TextView) view.findViewById(R.id.titlename);
            email = (TextView) view.findViewById(R.id.email);
            userid = (TextView) view.findViewById(R.id.userid);
            moreGear = (ImageButton) view.findViewById(R.id.moreGear);
        }
}
