package com.learnandroid.appworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class EditNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);
        setTitle(getString(R.string.empty_string));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_delete_data).setVisible(false);
        menu.findItem(R.id.action_menu_logout).setVisible(false);
        menu.findItem(R.id.action_sample_data).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }
}
