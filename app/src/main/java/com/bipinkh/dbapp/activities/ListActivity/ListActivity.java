package com.bipinkh.dbapp.activities.ListActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bipinkh.dbapp.R;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toast.makeText(this, "Check", Toast.LENGTH_SHORT).show();
    }
}
