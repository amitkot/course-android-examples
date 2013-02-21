package com.amitkotlovski.android.course.asyncAsync;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Params class type is Object - this was done as a response to the question
 * 		what would we do if we had to pass parameters with different types. In this
 * 		specific case, we could have passed Integers and saved ourselves the cast.
 * 
 * Progress is an Integer - we pass the current progress and the max value each
 * 		time. The max value is mandatory for Android to know how much of the progress
 * 		has already been achieved. It doesn't have to be passed every single time,
 * 		but as we only know it during the doInBackground method, onProgressUpdate()
 * 		is the first time we have the maximum progress possible and because we also
 * 		have to use it to update the _current_ progress, I chose to simple update
 * 		both at the same time.
 * 
 * Result is an Integer - the sum of all the desired elements.
 * 
 */
class AsyncTimeWaster extends AsyncTask<Object, Integer, Integer> {

	private Context mCtx;
	private TextView mProg;
	private ProgressBar mBar;
	
	/**
	 * 
	 * @param ctx
	 * @param prog a TextView to update with the current progress
	 * @param bar a ProgressBar to indicate how much progress was achieved so far
	 */
	public AsyncTimeWaster(Context ctx, TextView prog, ProgressBar bar) {
		mCtx = ctx;
		mProg = prog;
		mBar = bar;
	}

	/**
	 * Runs on a background thread, so it does not stop the main UI thread
	 */
	@Override
	protected Integer doInBackground(Object... params) {
		Integer max = (Integer) params[0];
		Integer start = (Integer) params[1];
		
		Integer res = timeWaster(max, start);
		return res;
	}

	/**
	 * Runs on the UI thread after the doInBackground method is done
	 */
	@Override
	protected void onPostExecute(Integer result) {
		Toast.makeText(mCtx, "result is " + result , Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Runs on the UI thread after receiving an update sent during the running
	 * of the doInBackground method
	 */
	@Override
	protected void onProgressUpdate(Integer... values) {
		Integer cur = values[0];
		Integer max = values[1];
		mProg.setText(String.valueOf(cur));
		mBar.setMax(max.intValue());
		mBar.setProgress(cur.intValue());
		
	}
	
	/**
	 * Runs on the UI thread after the AsyncTask has been canceled.
	 */
	@Override
	protected void onCancelled() {
		mBar.setProgress(0);
		mProg.setText("Canceled!");
	}
	
	private int timeWaster(Integer max, Integer start) {
		int sum = 0;
		for (int i = start; i < max; i++) {
			sum += i;
			
			if (i%10000 == 0) {
				publishProgress(i, max);
			}
			// TODO comment these lines to disable the option to stop the task during runtime. See how much faster the code is without it.
			// Escape early if cancel() is called
            if (isCancelled()) {
            	break;
            }
		}
		return sum;
	}

	// not the best solution
//	void setProg(TextView prog) {
//		mProg = prog;
//	}
}
