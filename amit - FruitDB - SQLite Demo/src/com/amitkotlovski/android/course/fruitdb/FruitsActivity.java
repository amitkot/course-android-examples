package com.amitkotlovski.android.course.fruitdb;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class FruitsActivity extends Activity {

	private TextView mAllFruits;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fruits);
		mAllFruits = (TextView) findViewById(R.id.all_fruits);

		FruitData db = new FruitData(this);
		insertFruits(db);

		Cursor cursor = db.getAllFruits();
		startManagingCursor(cursor);
		showFruits(cursor);
	}

	private void insertFruits(FruitData db) {
		Cursor cursor = db.getAllFruits();
		if (!cursor.moveToFirst()) { // list is empty
			db.addFruit(new Fruit("Banana", "sweet", 80.0));
			db.addFruit(new Fruit("Apple", "sweet", 120.0));
			db.addFruit(new Fruit("Grapefruit", "sour", 120.0));
			db.addFruit(new Fruit("Red Grapefruit", "sweet", 200.0));
			db.addFruit(new Fruit("Apple", "super sweet", 110.0));
			db.addFruit(new Fruit("Apple", "sour", 70.0));
			db.addFruit(new Fruit("Apricot", "sweet", 60.0));
			db.addFruit(new Fruit("Avocado", "sweet if ripe", 350.0));
		}
	}

	private void showFruits(Cursor cursor) {
		StringBuilder sb = new StringBuilder();
		sb.append("Saved fruits are:\n\n")
				.append("id\t\t").append("name\t\t").append("taste\t\t").append("weight\n")
				.append("=================================\n");

		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String name = cursor.getString(1);
			String taste = cursor.getString(2);
			double weight = cursor.getDouble(3);

			sb.append(id).append("\t\t").append(name).append("\t\t")
					.append(taste).append("\t\t").append(weight).append("\n");
		}

		mAllFruits.setText(sb);
	}
}
