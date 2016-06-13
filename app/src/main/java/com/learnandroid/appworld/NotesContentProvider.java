package com.learnandroid.appworld;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

public class NotesContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.learnandroid.appworld.notescontentprovider";
    private static final String BASE_PATH = "notes";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    // Constant to identify the requested operation
    private static final int NOTES = 1;
    private static final int NOTES_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, NOTES);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", NOTES_ID);
    }

    SQLiteDatabase mDatabase;
    NotesDatabaseHelper mNotesHelper;

    @Override
    public boolean onCreate() {
        mNotesHelper = new NotesDatabaseHelper(getContext());
        mDatabase = mNotesHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        return mDatabase.query(NotesDatabaseHelper.TABLE_NOTES, NotesDatabaseHelper.ALL_COLUMNS,
                selection, null, null, null, NotesDatabaseHelper.NOTE_CREATED + " DESC");
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Long id = mDatabase.insert(NotesDatabaseHelper.TABLE_NOTES, null, values);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mDatabase.delete(NotesDatabaseHelper.TABLE_NOTES, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return mDatabase.update(NotesDatabaseHelper.TABLE_NOTES, values, selection, selectionArgs);
    }
}
