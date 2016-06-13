package com.learnandroid.appworld;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class EditNotesActivity extends AppCompatActivity {

    private String mCurrentAction, mNoteFilter, mOldText;

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
            setTitle("New note");
        } else {
            mCurrentAction = Intent.ACTION_EDIT;
            setTitle("Edit note");
            mNoteFilter = NotesDatabaseHelper.NOTE_ID + "=" + uri.getLastPathSegment();
            Cursor c = getContentResolver().query(uri,NotesDatabaseHelper.ALL_COLUMNS,
                    mNoteFilter, null, null);
            if (c != null) {
                c.moveToFirst();
                mOldText = c.getString(c.getColumnIndex(NotesDatabaseHelper.NOTE_TEXT));
                mEditText.setText(mOldText);
                mEditText.requestFocus();
                c.close();
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mCurrentAction.equals(Intent.ACTION_EDIT)) {
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
        menu.findItem(R.id.action_delete_note).setVisible(true);
        menu.findItem(R.id.action_delete_data).setVisible(false);
        menu.findItem(R.id.action_menu_logout).setVisible(false);
        menu.findItem(R.id.action_sample_data).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                finishEditing();
                break;
            case R.id.action_delete_note:
                deleteNote();
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteNote() {
        getContentResolver().delete(NotesContentProvider.CONTENT_URI, mNoteFilter, null);
        Toast.makeText(EditNotesActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
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
             break;
            case Intent.ACTION_EDIT:
                if (newText.length() == 0) {
                    deleteNote();
                } else if (mOldText.equals(newText)) {
                    setResult(RESULT_CANCELED);
                } else {
                    updateNote(newText);
                }
        }
        finish();
    }

    private void updateNote(String noteText) {
        ContentValues values = new ContentValues();
        values.put(NotesDatabaseHelper.NOTE_TEXT, noteText);
        getContentResolver().update(NotesContentProvider.CONTENT_URI, values, mNoteFilter, null);
        Toast.makeText(EditNotesActivity.this, "Note Updated!", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
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
