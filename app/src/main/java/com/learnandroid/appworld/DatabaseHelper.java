package com.learnandroid.appworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    private static final String SEARCH_QUERY = "select userName, password from" + TABLE_NAME;


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

    public void insertContactInfo(Contact contact) {

        msqlDB = this.getWritableDatabase();

        ContentValues conValues = new ContentValues();

        conValues.put(COLUMN_FIRST_NAME,contact.getName());
        conValues.put(COLUMN_UNAME,contact.getUsername());
        conValues.put(COLUMN_PASSWORD,contact.getPass());

        msqlDB.insert(TABLE_NAME,null,conValues);
        msqlDB.close();
    }

    public String searchPass(String userName) {

        msqlDB =  getReadableDatabase();
        Cursor cursor = msqlDB.rawQuery(SEARCH_QUERY,null);
        String pass, userN;
        userN = "not found";
        if(cursor.moveToFirst())
        {
            do {

                pass = cursor.getString(0);

                if (pass.equals(userName)) {

                    userN = cursor.getString(1);
                    break;
                }


            } while (cursor.moveToNext());
        }

        return userN;
    }
}
