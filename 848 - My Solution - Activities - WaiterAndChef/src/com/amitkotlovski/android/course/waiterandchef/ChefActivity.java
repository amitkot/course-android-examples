package com.amitkotlovski.android.course.waiterandchef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.amitkotlovski.android.course.waiterandchef.R;

public class ChefActivity extends Activity {

	public static final String ACTUAL_DISH_PREPARED = "actual_dish_prepared";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.chef_layout);
		
		Intent intent = getIntent();
		String requestedDish = intent.getExtras().getString(WaiterActivity.DISH_REQUEST_KEY);
		
		TextView requestedDishTextView = (TextView) findViewById(R.id.requestedDishTextView);
		requestedDishTextView.setText("The requested dish is " + requestedDish);
		
		final EditText actualDishPreparedEditText = (EditText) findViewById(R.id.preparedDishEditText);
		
		Button returnDish = (Button) findViewById(R.id.sendDishBackButton);
		returnDish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String actualDishPrepared = actualDishPreparedEditText.getText().toString();
				
				Intent data = getIntent();
				data.putExtra(ACTUAL_DISH_PREPARED, actualDishPrepared);
				setResult(RESULT_OK, data);
				finish();
			}
		});
	}
}
