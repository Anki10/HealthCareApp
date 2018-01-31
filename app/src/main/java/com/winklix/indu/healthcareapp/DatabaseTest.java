package com.winklix.indu.healthcareapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by harsh on 03/01/2018.
 */

public class DatabaseTest extends SQLiteOpenHelper {

    private static final String TAG = DatabaseTest.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "demo_db";
    private static final String TABLE_NAME = "demo";
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PHONE = "user_phone";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_PROFILE = "user_profile";

    public DatabaseTest(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Create_Table = "CREATE TABLE" + TABLE_NAME + "("+ USER_ID + "INTEGER PRIMARY KEY," + USER_NAME + " TEXT,"
                               + USER_EMAIL + " TEXT," + USER_PHONE + " TEXT," + USER_PASSWORD + " TEXT,"+ USER_PROFILE + " BLOB"+")";

        db.execSQL(Create_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(String user_id, String user_name, String user_email, String user_phone, String user_password, byte[] user_profile) {
        SQLiteDatabase dbs = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(USER_ID , user_id);
        cv.put(USER_NAME , user_name);
        cv.put(USER_EMAIL , user_email);
        cv.put(USER_PHONE , user_phone);
        cv.put(USER_PASSWORD , user_password);
        cv.put(USER_PROFILE , user_profile);

        long id = dbs.insert(TABLE_NAME,null,cv);
        dbs.close();

        Log.d(TAG , "insert" + id);
    }

    
}
