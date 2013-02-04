package com.amitkotlovski.android.course.smallcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amitkotlovski.android.course.smallcalculator.R;

public class SmallCalcActivity extends Activity {

	protected EditText mInput;
	protected Button mCalculate;
	protected TextView mResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mInput = (EditText) findViewById(R.id.input);
		mCalculate = (Button) findViewById(R.id.button);
		mResult = (TextView) findViewById(R.id.result);

		mCalculate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mResult.setText("");
				String numberString = mInput.getText().toString();
				try {
				int number = Integer.valueOf(numberString);
				int res = number + 66;
				mResult.setText(String.valueOf(res));
				} catch (NumberFormatException nfe) {
					Toast.makeText(SmallCalcActivity.this, "Please insert a number", Toast.LENGTH_LONG).show();
					mInput.setText("");
				}
			}
		});
	}
}
