package com.learnandroid.appworld;


import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EDITOR_REQUEST_CODE = 1199;
    private TextView mTextUser, mTextUser2;

    private String mFullName, mUserName;

    private Intent newIntent;
    AlertDialog.Builder mAlertDialog, mAlert, mDeleteNotesDialog;

    private Cursor mCursor;

    private CursorAdapter mCursorAdapter;

    private FloatingActionButton mAddNoteBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mFullName = getIntent().getStringExtra("FullName");
        setTitle(getString(R.string.empty_string));
        init();
        insertNote("New Note");

        mCursorAdapter = new NotesCustomCursorAdapter(this, null, 0);

        ListView listView = (ListView) findViewById(android.R.id.list);
        if (listView != null) {
            listView.setAdapter(mCursorAdapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(UserActivity.this, EditNotesActivity.class);
                Uri uri =  Uri.parse(NotesContentProvider.CONTENT_URI + "/" + id);
                intent.putExtra(NotesContentProvider.CONTENT_TYPE, uri);
                startActivityForResult(intent, EDITOR_REQUEST_CODE);
            }
        });
        getLoaderManager().initLoader(0, null, UserActivity.this);
    }

    private void insertNote(String newNote) {
        ContentValues values = new ContentValues();
        values.put(NotesDatabaseHelper.NOTE_TEXT, newNote);
        Uri noteUri = getContentResolver().insert(NotesContentProvider.CONTENT_URI, values);
        if (noteUri != null) {
            Log.d("UserActivity", "Inserted Note" + noteUri.getLastPathSegment());
            Toast.makeText(UserActivity.this, "New note added", Toast.LENGTH_SHORT).show();
        }
        if (mCursor != null) {
            mCursor.close();
        }
    }

    private void init() {

//        mTextUser = (TextView) findViewById(R.id.userActivityTVL);
//
//        mTextUser2 = (TextView) findViewById(R.id.userActivityTVL2);

        mAddNoteBtn = (FloatingActionButton) findViewById(R.id.btn_add_new_note);

        onAddNoteClickEvent();

        if (mTextUser2 != null) {
            mTextUser2.setText(mFullName);
        }
    }

    private void onAddNoteClickEvent() {
        mAddNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditNotesActivity();
            }
        });
    }

    @Override
    public void onBackPressed() {
        mAlert = new AlertDialog.Builder(UserActivity.this,
                R.style.AppCompatAlertDialogStyle);
        mAlert.setMessage("Do you want to logout?");
        mAlert.setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showLogin();
            }
        });
        mAlert.setNegativeButton(getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        if (mAlert != null) {
            mAlert.show();
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        menu.findItem(R.id.action_delete_note).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_logout:
                showLogOffDialog();
                break;
            case R.id.action_sample_data:
                insertSampleData();
                break;
            case R.id.action_delete_data:
                deleteAllNotes();
                break;
            default:
        }
                return super.onOptionsItemSelected(item);
    }

    private void deleteAllNotes() {
        mDeleteNotesDialog = new AlertDialog.Builder(this,R.style.AppCompatAlertDialogStyle);
        mDeleteNotesDialog.setTitle("CAUTION: DELETING NOTES");
        mDeleteNotesDialog.setMessage("Are you sure you want to delete all notes?");
        mDeleteNotesDialog.setPositiveButton(getString(R.string.dialog_yes),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getContentResolver().delete(NotesContentProvider.CONTENT_URI, null, null);
                restartLoader();
                Toast.makeText(UserActivity.this, "All notes deleted", Toast.LENGTH_SHORT).show();
            }
        });

        mDeleteNotesDialog.setNegativeButton(getString(R.string.dialog_no),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        if (mDeleteNotesDialog != null) {
            mDeleteNotesDialog.show();
        }

    }

    private void insertSampleData() {
        insertNote("New Simple Note");
        insertNote("New Multi-Line \n Note");
        insertNote("Very long note that exceeds the width of the screen. Be sure to check this one " +
                "out since it creates ellipses at the end");
        restartLoader();

    }

    private void restartLoader() {
        getLoaderManager().restartLoader(0, null, this);
    }

    private void showLogOffDialog() {
        mAlertDialog = new AlertDialog.Builder(UserActivity.this, R.style.AppCompatAlertDialogStyle);
        mAlertDialog.setMessage("Are you sure you want to log off?");
        mAlertDialog.setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showLogin();

            }
        });

        mAlertDialog.setNegativeButton(getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mAlertDialog.show();
    }

    private void showLogin() {
        finish();
        newIntent = new Intent(UserActivity.this, LoginActivity.class);
        startActivity(newIntent);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,NotesContentProvider.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

    private void launchEditNotesActivity() {
        Intent editorActivity =  new Intent(this, EditNotesActivity.class);
        startActivityForResult(editorActivity, EDITOR_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EDITOR_REQUEST_CODE && resultCode == RESULT_OK) {
            restartLoader();
        }
    }
}
