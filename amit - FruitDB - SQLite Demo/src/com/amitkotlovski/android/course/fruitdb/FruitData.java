package com.amitkotlovski.android.course.fruitdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FruitData {

	private FruitSqliteOpenHelper mDbHelper;

	private class FruitSqliteOpenHelper extends SQLiteOpenHelper {

		private static final String TAG = "FruitSqliteOpenHelper";
		public static final String DB_NAME = "fruits.db";
		public static final int DB_VERSION = 1;
	

		public FruitSqliteOpenHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		/**
		 * Create the fruits table
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + FruitContract.Fruits.TABLE_NAME + " ("
					+ FruitContract.Fruits._ID + " INTEGER PRIMARY KEY,"
					+ FruitContract.Fruits.COLUMN_NAME_NAME + " TEXT,"
					+ FruitContract.Fruits.COLUMN_NAME_TASTE + " TEXT,"
					+ FruitContract.Fruits.COLUMN_NAME_WEIGHT + " REAL" 
					+ ");");
		}

		/**
		 * Handles the upgrade to a new version. This implementation deletes the
		 * data in the old version of the table. In some applications there
		 * might be a need to move that data to the new table.
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Logs that the database is being upgraded
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");

			// Kills the table and existing data
			db.execSQL("DROP TABLE IF EXISTS " + FruitContract.Fruits.TABLE_NAME);

			// Recreates the database with a new version
			onCreate(db);
		}

	}

	public FruitData(Context context) {
		mDbHelper = new FruitSqliteOpenHelper(context);
	}

	public Cursor getAllFruits() {
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		Cursor cursor = db.query(
				FruitContract.Fruits.TABLE_NAME,	// The table to query
				null,                               // The columns to return
				null,								// no columns for the WHERE clause
				null,                            	// no values for the WHERE clause
				null,                               // don't group the rows
				null,                               // don't filter by row groups
				null                                // don't sort
				);
		return cursor;
	}

	public Fruit getFruit(int id) {
		Fruit res = null;
		SQLiteDatabase db = mDbHelper.getReadableDatabase();

		String selection = FruitContract.Fruits._ID + "=?";
		String[] selectionArgs = { String.valueOf(id) };
		Cursor cursor = db.query(
				FruitContract.Fruits.TABLE_NAME,	// The table to query
				null,                               // The columns to return or null for all
				selection,                          // The columns for the WHERE clause
				selectionArgs,                      // The values for the WHERE clause
				null,                               // don't group the rows
				null,                               // don't filter by row groups
				null								// don't sort
				);
		if (cursor.moveToFirst()) {
			int resId = cursor.getInt(0);
			String resName = cursor.getString(1);
			String resTaste = cursor.getString(2);
			double resWeight = cursor.getDouble(3);

			res = new Fruit(resId, resName, resTaste, resWeight);
		}
		return res;
	}

	public void addFruit(Fruit fruit) {
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		ContentValues fruitValues = new ContentValues();
		fruitValues.put(FruitContract.Fruits.COLUMN_NAME_NAME, fruit.getName());
		fruitValues.put(FruitContract.Fruits.COLUMN_NAME_TASTE, fruit.getTaste());
		fruitValues.put(FruitContract.Fruits.COLUMN_NAME_WEIGHT, fruit.getWeight());

		db.insert(FruitContract.Fruits.TABLE_NAME, null, fruitValues);
	}

	public void updateFruitTaste(Fruit fruit) {
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		ContentValues fruitValues = new ContentValues();
		fruitValues.put(FruitContract.Fruits.COLUMN_NAME_TASTE, fruit.getTaste());

		String whereTemplate = FruitContract.Fruits._ID + "=?";
		String[] whereArgs = { String.valueOf(fruit.getId()) };
		db.update(
				FruitContract.Fruits.COLUMN_NAME_NAME,	// table name
				fruitValues,							// data to update
				whereTemplate, 							// columns for the WHERE clause
				whereArgs								// values for the WHERE clause
				);
	}

	public void deleteFruit(Fruit fruit) {
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		String whereClause = FruitContract.Fruits._ID + "=?";
		String[] whereArgs = { String.valueOf(fruit.getId()) };
		db.delete(
				FruitContract.Fruits.COLUMN_NAME_NAME,	// table name
				whereClause,							// columns for the WHERE clause
				whereArgs								// values for the WHERE clause
				);
	}
}
