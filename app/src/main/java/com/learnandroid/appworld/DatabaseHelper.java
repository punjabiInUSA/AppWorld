package com.learnandroid.appworld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1001;

    private static final String DATABASE_NAME = "contact.db";

    private static final String TABLE_NAME = "contactTable";

    private static final String COLUMN_FIRST_NAME = "firstName";

    private static final String COLUMN_LAST_NAME = "lastName";

    private static final String COLUMN_PASSWORD = "password";

    private static final String COLUMN_UNAME = "userName";

    private static final String COLUMN_ID = "id";

    private SQLiteDatabase msqlDB;

    private static final String TABLE_CREATE = "create table contactTable (id integer primary key " +
            "not null auto_increment, " + "firstName text not null" + "password text not null" +
            "userName text not null)";



    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.msqlDB =  db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS" + TABLE_CREATE;
        msqlDB.execSQL(query);
        this.onCreate(db);

    }
}
