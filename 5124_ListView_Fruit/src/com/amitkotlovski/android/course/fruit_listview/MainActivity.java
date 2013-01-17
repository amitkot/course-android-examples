package com.amitkotlovski.android.course.fruit_listview;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String[] data = { "Apple", "Banana", "Apricot", "Orange", "Watermelon",
				"Kiwi", "Raspberry", "Pineapple", "Lychee", "Tomato", "Grape", "Peach" };
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
						MainActivity.this,
						String.format(
								"The fruit is %s, position is %d, id is %d",
								fruit, position, id), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
