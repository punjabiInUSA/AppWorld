package com.learnandroid.appworld;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class EditNotesActivity extends AppCompatActivity {

    private String mCurrentAction;

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);
        setTitle(getString(R.string.empty_string));

        mEditText = (EditText) findViewById(R.id.et_notes);

        Intent intent = getIntent();

        Uri uri = intent.getParcelableExtra(NotesContentProvider.CONTENT_TYPE);

        if(uri == null) {
            mCurrentAction = Intent.ACTION_INSERT;
            setTitle("New Note");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
        menu.findItem(R.id.action_delete_data).setVisible(false);
        menu.findItem(R.id.action_menu_logout).setVisible(false);
        menu.findItem(R.id.action_sample_data).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                finishEditing();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void finishEditing() {
        //Trim will make sure I eliminate any white spaces
        String newText = mEditText.getText().toString().trim();

        switch (mCurrentAction) {
            case Intent.ACTION_INSERT:
                if (newText.length() == 0) {
                    setResult(RESULT_CANCELED);
                } else {
                    insertNewNote(newText);
                }
        }
        finish();
    }

    private void insertNewNote(String noteText) {
        ContentValues values = new ContentValues();
        values.put(NotesDatabaseHelper.NOTE_TEXT, noteText);
        getContentResolver().insert(NotesContentProvider.CONTENT_URI, values);
        setResult(RESULT_OK);
    }

    @Override
    public void onBackPressed() {
        finishEditing();
    }
}
