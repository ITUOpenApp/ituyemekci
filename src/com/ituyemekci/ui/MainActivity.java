package com.ituyemekci.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.ituopenapp.ituyemekci.R;
import com.ituyemekci.core.Commons;
import com.ituyemekci.parser.JsonParser;

public class MainActivity extends Activity {

	public TextView textView;
	public ImageView logo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("kod", Commons.OnCreateLogMessage);

		// Create the text view
		this.textView = new TextView(this);
		this.textView.setTextSize(30);

		//Create image view
		try {
			Drawable d = Drawable.createFromStream(getAssets().open("ituopenapp_logo.png"), null);
			this.logo = new ImageView(this);  //(ImageView) findViewById(R.id.full_image_view);
			this.logo.setImageDrawable(d);
		}catch(Exception e) {
			Log.v("kod", e.toString());
		}
		

		this.logo.setScaleType(ImageView.ScaleType.CENTER_CROP  );
		setContentView(this.logo);
		
		
		new ParseTask().execute();
		// Set the text view as the activity layout
		//setContentView(this.textView);
	}

	protected void onResume() {
		super.onResume();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	  super.onConfigurationChanged(newConfig);
	  setContentView(MainActivity.this.textView);
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
			//sendMessage(result);
			MainActivity.this.textView.setText(result);
			MainActivity.this.setContentView(MainActivity.this.textView);
		}

		@Override
		protected String doInBackground(Void... params) {
			return JsonParser.getContent();
		}
	}
}
