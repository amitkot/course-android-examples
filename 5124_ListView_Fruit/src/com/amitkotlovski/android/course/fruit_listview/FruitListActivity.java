package com.amitkotlovski.android.course.fruit_listview;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FruitListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ArrayList<String> data = new ArrayList<String>();
		data.add("Apple");
		data.add("Banana");
		data.add("Apricot");
		data.add("Orange");
		data.add("Watermelon");
		data.add("Kiwi");
		data.add("Raspberry");
		data.add("Pineapple");
		data.add("Lychee");
		data.add("Tomato");
		data.add("Peach");
		ArrayAdapter<String> fruits = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);

		setListAdapter(fruits);

		ListView listView = getListView();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String fruit = (String) parent.getItemAtPosition(position);
				Toast.makeText(
						FruitListActivity.this,
						String.format(
								"The fruit is %s, position is %d, id is %d",
								fruit, position, id), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
