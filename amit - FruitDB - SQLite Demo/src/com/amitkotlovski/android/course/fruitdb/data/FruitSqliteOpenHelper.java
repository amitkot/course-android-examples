package com.amitkotlovski.android.course.fruitdb.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FruitSqliteOpenHelper extends SQLiteOpenHelper {

	private static final String TAG = "FruitSqliteOpenHelper";

	public FruitSqliteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ").append(FruitDbConstants.FRUITS_TABLE_NAME).append(" (")
				.append(FruitDbConstants.FRUIT_ID).append(" INTEGER PRIMARY KEY, ")
				.append(FruitDbConstants.FRUIT_NAME).append(" TEXT, ")
				.append(FruitDbConstants.FRUIT_TASTE).append(" TEXT, ")
				.append(FruitDbConstants.FRUIT_WEIGHT).append(" REAL")
				.append(");");
		
		String createString = sb.toString();
		try {
			db.execSQL(createString);
		} catch (SQLiteException e) {
			Log.e(TAG, "CREATE TABLE failed: " + e.getMessage());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			db.execSQL("DROP TABLE IF EXISTS " + FruitDbConstants.FRUITS_TABLE_NAME);
		} catch (SQLiteException e) {
			Log.e(TAG, "DROP TABLE failed: " + e.getMessage());
		}
		onCreate(db);
	}

}
