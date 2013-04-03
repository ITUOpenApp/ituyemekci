package com.ituyemekci.parser;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ituyemekci.core.Commons;

import android.util.Log;

public class JsonParser {
	public static String getContent(){
		StringBuilder sb = new StringBuilder();
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(Commons.JsonApiUrl);
			HttpResponse response = client.execute(httpGet);
			InputStream content = response.getEntity().getContent();
			String jsonString =  convertStreamToString(content);
			JSONObject object = new JSONObject(jsonString);
			
			sb.append(object.getString("Date"));
			sb.append("\n\n");
			JSONArray arr = object.getJSONArray("Dishes");

			for (int i = 0; i < arr.length(); i++) {
				sb.append("*");
				sb.append(arr.getString(i));
				sb.append("\n");
			}
			
			sb.append("\n");
			sb.append(object.getString("Note"));
			
			Log.v("kod", object.getString("Note"));
		} catch (Exception e) {
			Log.v("kod", e.toString());
		}
		
		return sb.toString();
	}
	


	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
}
