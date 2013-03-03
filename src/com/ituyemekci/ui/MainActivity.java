package com.ituyemekci.ui;

import com.example.ituyemekci.R;
import com.ituyemekci.core.Commons;
import com.ituyemekci.parser.HtmlParser;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("kod", Commons.OnCreateLogMessage);

		// Create the text view
		TextView textView = new TextView(this);
		textView.setTextSize(40);

		new ParseTask().execute();
		// Set the text view as the activity layout
		setContentView(textView);
	}

	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void sendMessage(String message) {
		Intent intent = new Intent(this, DisplayMenuActivity.class);
		intent.putExtra("menu_content", message);
		startActivity(intent);
	}

	private class ParseTask extends AsyncTask<Void, Void, String> {
		protected void onPostExecute(String result) {
			sendMessage(result);
		}

		@Override
		protected String doInBackground(Void... params) {
			return HtmlParser.getContent();
		}
	}
}
