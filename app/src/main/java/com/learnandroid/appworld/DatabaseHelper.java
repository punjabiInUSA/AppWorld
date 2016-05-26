package com.learnandroid.appworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "info.db";

    private static final String TABLE_NAME = "information";

    private static final String COLUMN_FIRST_NAME = "fName";

    private static final String COLUMN_PASSWORD = "pwd";

    private static final String COLUMN_UNAME = "uName";

    private static final String COLUMN_ID = "id";

    private SQLiteDatabase msqlDB;

    private static final String TABLE_CREATE = "create table information (id integer primary key not null,"
            + "fName text not null, pwd text not null, uName text not null);";

    private static final String SEARCH_QUERY = "select uName, pwd from " +TABLE_NAME;

    private static final String SEARCH_NAME = "select uName, fName from " +TABLE_NAME;

    private static final String ANOTHER_QUERY = "select * from " +TABLE_NAME;



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

    public void insertContactInfo(userInfo usrinfo) {

        msqlDB = this.getWritableDatabase();

        ContentValues conValues = new ContentValues();


        Cursor c = msqlDB.rawQuery(ANOTHER_QUERY,null);
        int count =  c.getCount();

        conValues.put(COLUMN_ID,count);
        conValues.put(COLUMN_FIRST_NAME,usrinfo.getName());
        conValues.put(COLUMN_UNAME,usrinfo.getUsername());
        conValues.put(COLUMN_PASSWORD,usrinfo.getPass());

        msqlDB.insert(TABLE_NAME,null,conValues);
        msqlDB.close();
        c.close();
    }

    public String searchPass(String userName) {

        msqlDB =  this.getReadableDatabase();
        Cursor cursor = msqlDB.rawQuery(SEARCH_QUERY,null);
        String pass, userN;
        pass = "no password detected";

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

    public String searchFName(String uSerName) {
        msqlDB =  this.getReadableDatabase();
        Cursor cursor = msqlDB.rawQuery(SEARCH_NAME,null);
        String fname,useR;
        fname = "no user detected";

        if(cursor.moveToFirst())
        {
            do {

                useR = cursor.getString(0);

                if (useR.equals(uSerName)) {
                    fname = cursor.getString(1);
                    break;
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        return fname;
    }

}
