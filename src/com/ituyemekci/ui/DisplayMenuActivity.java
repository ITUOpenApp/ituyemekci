package com.ituyemekci.ui;

import com.example.ituyemekci.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class DisplayMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra("menu_content");

	    // Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(30);
	    textView.setText(message);

	    // Set the text view as the activity layout
	    setContentView(textView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_menu, menu);
		return true;
	}

}