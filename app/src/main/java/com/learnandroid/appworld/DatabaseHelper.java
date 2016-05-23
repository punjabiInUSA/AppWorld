package com.learnandroid.appworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "contact.db";

    private static final String TABLE_NAME = "contacttable";

    private static final String COLUMN_FIRST_NAME = "firstname";

    private static final String COLUMN_PASSWORD = "password";

    private static final String COLUMN_UNAME = "username";

    private static final String COLUMN_ID = "id";

    private SQLiteDatabase msqlDB;

    private static final String TABLE_CREATE = "create table contacttable (id integer primary key not null,"
            + "firstname text not null, password text not null, username text not null);";

    private static final String SEARCH_QUERY = "select username, password from " +TABLE_NAME;

    private static final String ANOTHER_QUERY = "select * from contacttable";



    public DatabaseHelper(Context context) {
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


        Cursor c = msqlDB.rawQuery(ANOTHER_QUERY,null);
        int count =  c.getCount();

        conValues.put(COLUMN_ID,count);
        conValues.put(COLUMN_FIRST_NAME,contact.getName());
        conValues.put(COLUMN_UNAME,contact.getUsername());
        conValues.put(COLUMN_PASSWORD,contact.getPass());

        msqlDB.insert(TABLE_NAME,null,conValues);
        msqlDB.close();
        c.close();
    }

    public String searchPass(String userName) {

        msqlDB =  this.getReadableDatabase();
        Cursor cursor = msqlDB.rawQuery(SEARCH_QUERY,null);
        String pass, userN;
        pass = "not found";

        if(cursor.moveToFirst())
        {
            do {

                userN = cursor.getString(0);

                if (userN.equals(userName)) {
                    pass = cursor.getString(1);
                    break;
                }

            } while (cursor.moveToNext());
        }
            cursor.close();
        return pass;
    }
}
