package com.bipinkh.dbapp.functions.optionsMenu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bipinkh.dbapp.R;
import com.bipinkh.dbapp.activities.Add_Edit_Form.Edit_Add_Form;
import com.bipinkh.dbapp.activities.ListActivity.ListActivity;
import com.bipinkh.dbapp.functions.database.deleteData;
import com.bipinkh.dbapp.models.database.User;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

/**
 * Created by bipin on 11/9/2017.
 */

public class edit_delete_menu {

    public static void popup(View v,final Long user_id, View moreGear){
        final Context context = v.getContext();
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(context, moreGear);
        popup.getMenuInflater().inflate(R.menu.list_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                String title = item.getTitle().toString();
                    //edit option selected
                if (title.equals("Edit")){
                   edituser(context, user_id);
                }else
                if(title.equals("Delete")){
                    confirmDeleteUser(user_id, context);
                }
                return true;
            }
        });
        popup.show();
    }


    private static void edituser(Context context,Long user_id) {
        Intent i = new Intent(context, Edit_Add_Form.class);
        i.putExtra("editing", Boolean.TRUE);
        i.putExtra("userid", user_id);
        context.startActivity(i);
    }


    private static void confirmDeleteUser(final Long user_id, final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to delete ?");
        builder.setPositiveButton("Yes !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new deleteData().deleteUser(user_id);
                Intent i = new Intent(context,ListActivity.class);
                i.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
            }
        });
        builder.setNegativeButton("No !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}
