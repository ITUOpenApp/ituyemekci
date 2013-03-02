package com.ui.ituyemekci;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.example.ituyemekci.R;

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
		Log.v("kod","test");
		//setContentView(R.layout.activity_main);

		String title="...";
		
	    // Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(40);
	    
	    


	    new ParseTask().execute("http://www.sks.itu.edu.tr/");
		


	    // Set the text view as the activity layout
	    setContentView(textView);
		
		
	}

	protected void onResume()
	{
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
	
	
	private class ParseTask extends AsyncTask<String, Void, String> {
		protected String doInBackground(String... params) {
			//int count = urls.length;
			String url=params[0];
			String menu_content="baðlanamadý..";
			try {
				
				Document doc = Jsoup.connect("http://www.sks.itu.edu.tr/").timeout(0).get();
				menu_content = doc.select("div#rightcolumn  td.mod_events_latest_first").text() ;
				//textView.setText(title);
				} catch(Exception e) {
					Log.v("kod",e.toString());
				}

			return menu_content;
	     }

		   protected void onPostExecute(String result) {
		         sendMessage(result);
		     }

		

		}


	
	
	
	
}
