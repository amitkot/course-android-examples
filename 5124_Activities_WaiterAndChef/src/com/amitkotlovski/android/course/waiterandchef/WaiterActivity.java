package com.amitkotlovski.android.course.waiterandchef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amitkotlovski.android.course.waiterandchef.R;

public class WaiterActivity extends Activity {

	public static final String DISH_REQUEST_KEY = "dish_request";
	protected static final int DISH_REQUEST_CODE = 5666;
	private TextView mActualDishTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText dishRequestEditText = (EditText) findViewById(R.id.dishRequestEditText);

		mActualDishTextView = (TextView) findViewById(R.id.actualDishTextView);

		Button button = (Button) findViewById(R.id.sendToChefButton);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mActualDishTextView.setText("");

				String dishRequestText = dishRequestEditText.getText()
						.toString();

				Intent intent = new Intent(WaiterActivity.this,
						ChefActivity.class);
				intent.putExtra(DISH_REQUEST_KEY, dishRequestText);
				startActivityForResult(intent, DISH_REQUEST_CODE);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == DISH_REQUEST_CODE) {
			String actualDishPrepared = data.getExtras().getString(
					ChefActivity.ACTUAL_DISH_PREPARED);
			mActualDishTextView.setText("The actual dish prepared was "
					+ actualDishPrepared);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
