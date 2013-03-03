package com.ituyemekci.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.ituyemekci.core.Commons;

import android.util.Log;

public class HtmlParser {
	public static String getContent(){
		StringBuilder sb = new StringBuilder();
		try {
			Document doc = Jsoup.connect(Commons.SKSUrl)
					.timeout(0).get();
			sb.append(doc.select(
					Commons.JsoupQuery).text());
		} catch (Exception e) {
			Log.v("kod", e.toString());
		}
		
		return sb.toString();
	}
}
