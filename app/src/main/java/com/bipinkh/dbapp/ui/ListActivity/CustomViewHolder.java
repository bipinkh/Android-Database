package com.bipinkh.dbapp.ui.ListActivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bipinkh.dbapp.R;

/**
 * Created by bipin on 11/8/2017.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView mListName, mListEmail, mListId;
        public ImageButton mListGear;

        public CustomViewHolder(final View view) {
            super(view);
            mListName = (TextView) view.findViewById(R.id.text_ListName);
            mListEmail = (TextView) view.findViewById(R.id.text_ListEmail);
            mListId = (TextView) view.findViewById(R.id.text_ListId);
            mListGear = (ImageButton) view.findViewById(R.id.img_ListGear);
        }
}
