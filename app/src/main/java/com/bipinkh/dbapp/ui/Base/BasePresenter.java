package com.bipinkh.dbapp.ui.Base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bipinkh.dbapp.R;
import com.bipinkh.dbapp.services.daoServices.UserDaoService;
import com.bipinkh.dbapp.ui.Add_Edit_Form.Edit_Add_Form;
import com.bipinkh.dbapp.ui.ListActivity.ListActivity;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

/**
 * Created by bipin on 11/15/2017.
 */


public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

    public void popUpMenu(View v, final Long user_id, View moreGear){
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
                    confirmDeleteUser(context, user_id);
                }
                return true;
            }
        });
        popup.show();
    }

    public void edituser(Context context, Long userid){
        Intent i = new Intent(context, Edit_Add_Form.class);
        i.putExtra("editing", Boolean.TRUE);
        i.putExtra("userid", userid);
        context.startActivity(i);
    }

    public void confirmDeleteUser(final Context context, final Long userid){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to delete ?");
        builder.setPositiveButton("Yes !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new UserDaoService().deleteUser(userid);
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

