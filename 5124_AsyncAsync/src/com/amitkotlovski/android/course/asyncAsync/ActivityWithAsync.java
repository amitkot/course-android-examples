package com.amitkotlovski.android.course.asyncAsync;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amitkotlovski.android.course.asyncAsync.R;

public class ActivityWithAsync extends Activity {

	private static final int MAX = 100000000;
	private static final int START = 500;
	protected AsyncTimeWaster waster;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_with_async);

		final TextView prog = (TextView) findViewById(R.id.prog);
		final ProgressBar bar = (ProgressBar) findViewById(R.id.prog_bar);
		
		Button respond = (Button) findViewById(R.id.responsive);
		respond.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(ActivityWithAsync.this, "responsive",
						Toast.LENGTH_SHORT).show();
			}

		});

		Button waste = (Button) findViewById(R.id.waste_time);
		waste.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// long res = AsyncTimeWaster.timeWaster(MAX, START);
				waster = new AsyncTimeWaster(ActivityWithAsync.this, prog, bar);
				waster.execute(MAX, START);
				Toast.makeText(ActivityWithAsync.this, "result is big",
						Toast.LENGTH_SHORT).show();
			}
		});
		
		
		Button stop = (Button) findViewById(R.id.stop);
		stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (waster != null) {
					waster.cancel(true);
				}
			}
	
		});
		
	}

}
