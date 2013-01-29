package com.amitkotlovski.android.course.fruitdb.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class FruitHanlder {

	private static final String TAG = "FruitHandler";

	private FruitSqliteOpenHelper mDbHelper;

	public FruitHanlder(Context context) {
		mDbHelper = new FruitSqliteOpenHelper(context,
				FruitDbConstants.DB_NAME, null, FruitDbConstants.DB_VERSION);
	}

	public Cursor getAllFruits() {
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		Cursor cursor = db.query(FruitDbConstants.FRUITS_TABLE_NAME, null,
				null, null, null, null, null);
		return cursor;
	}

	public Fruit getFruit(int id) {
		Fruit res = null;
		SQLiteDatabase db = mDbHelper.getReadableDatabase();

		String tableName = FruitDbConstants.FRUITS_TABLE_NAME;
		String whereTemplate = FruitDbConstants.FRUIT_ID + "=?";
		String[] whereArgs = { String.valueOf(id) };
//		String whereTemplate = FruitDbConstants.FRUIT_ID + "=" + String.valueOf(id);
//		String[] whereArgs = null;
		Cursor cursor = db.query(tableName, null, whereTemplate, whereArgs,
				null, null, null);

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
		fruitValues.put(FruitDbConstants.FRUIT_NAME, fruit.getName());
		fruitValues.put(FruitDbConstants.FRUIT_TASTE, fruit.getTaste());
		fruitValues.put(FruitDbConstants.FRUIT_WEIGHT, fruit.getWeight());

		try {
			db.insertOrThrow(FruitDbConstants.FRUITS_TABLE_NAME, null,
					fruitValues);
		} catch (SQLiteException e) {
			Log.e(TAG, "Adding fruit failed: " + e.getMessage());
		} finally {
			db.close();
		}
	}

	public void updateFruitTaste(Fruit fruit) {
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		try {
			ContentValues fruitValues = new ContentValues();
			fruitValues.put(FruitDbConstants.FRUIT_TASTE, fruit.getTaste());

			String whereTemplate = FruitDbConstants.FRUIT_ID + "=?";
			String[] whereArgs = { String.valueOf(fruit.getId()) };
			db.update(FruitDbConstants.FRUITS_TABLE_NAME, fruitValues,
					whereTemplate, whereArgs);
		} catch (SQLiteException e) {
			Log.e(TAG, "Update faild: " + e.getMessage());
		} finally {
			db.close();
		}
	}

	public void deleteFruit(Fruit fruit) {
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		try {
			String whereClause = FruitDbConstants.FRUIT_ID + "=?";
			String[] whereArgs = { String.valueOf(fruit.getId()) };
			db.delete(FruitDbConstants.FRUITS_TABLE_NAME, whereClause,
					whereArgs);
		} catch (SQLiteException e) {
			Log.e(TAG, "Delete faild: " + e.getMessage());
		} finally {
			db.close();
		}
	}
}
