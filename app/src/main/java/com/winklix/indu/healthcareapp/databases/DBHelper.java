package com.winklix.indu.healthcareapp.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

	private static final String TAG = DBHelper.class.getSimpleName();

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "dr_reg_db";

	private static final String TABLE_USER = "dr_login";

	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_PHONE = "phone";
	private static final String KEY_PASWORD = "password";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_EMAIL + " TEXT UNIQUE," + KEY_PHONE + " TEXT,"
				+ KEY_PASWORD + " TEXT" + ")";
		db.execSQL(CREATE_LOGIN_TABLE);

		Log.d(TAG, "Database tables created");
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

		// Create tables again
		onCreate(db);
	}

	public void addUser(String name, String email, String phone, String password) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name);
		values.put(KEY_EMAIL, email);
		values.put(KEY_PHONE, phone);
		values.put(KEY_PASWORD, password);

		// Inserting Row
		long id = db.insert(TABLE_USER, null, values);
		db.close(); // Closing database connection

		Log.d(TAG, "New user inserted into sqlite: " + id);
	}

	public void addDoctor(String name, String email, String phone, String password) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name);
		values.put(KEY_EMAIL, email);
		values.put(KEY_PHONE, phone);
		values.put(KEY_PASWORD, password);

		// Inserting Row
		long id = db.insert(TABLE_USER, null, values);
		db.close(); // Closing database connection

		Log.d(TAG, "New user inserted into sqlite: " + id);
	}

	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_USER;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.put("name", cursor.getString(1));
			user.put("email", cursor.getString(2));
			user.put("phone", cursor.getString(3));
			user.put("password", cursor.getString(4));
		}
		cursor.close();
		db.close();

		Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

		return user;
	}

	public void deleteUsers() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_USER, null, null);
		db.close();

		Log.d(TAG, "Deleted all user info from sqlite");
	}

}
