package com.amitkotlovski.android.course.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * Connected to the button in the XML
	 */
	public void showAlertDialog(View v) {
		new AlertDialog.Builder(this).setTitle("Important Alert")
				.setMessage("What do you want to eat?")
				.setPositiveButton("Fruit", null)
				.setNegativeButton("Cookies", null)
				.setNeutralButton("Let's think", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this,
								"What's there to think about?",
								Toast.LENGTH_SHORT).show();
					}
				}).show();
	}

	/**
	 * Connected to the button in the XML
	 */
	public void showProgressDialog(View v) {
		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setTitle("Thinking");
		dialog.setMessage("Fruit or Cookies. Hmmmm...");
		dialog.setButton(Dialog.BUTTON_POSITIVE, "I Decided!",
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this, "Can I have both?",
								Toast.LENGTH_SHORT).show();
						dialog.dismiss();
					}
				});
		dialog.show();

	}

	/**
	 * Connected to the button in the XML
	 */
	public void showToast(View v) {
		Toast.makeText(this, "Can I have fruits with cookies and some Toast?",
				Toast.LENGTH_LONG).show();
	}
}
