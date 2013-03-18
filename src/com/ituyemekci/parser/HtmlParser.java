package com.ituyemekci.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.util.Log;

import com.ituyemekci.core.Commons;

public class HtmlParser {
	public static String getContent(){
		StringBuilder sb = new StringBuilder();
		try {
			Document doc = Jsoup.connect(Commons.SKSUrl)
					.timeout(0).get();
			sb.append(doc.select(
					Commons.JsoupQueryDate).get(0).text() + "\n\n");
			
			for(Element el : doc.select(Commons.JsoupQueryMenu)){
				sb.append(el.text() +"\n") ;
			}
			
		} catch (Exception e) {
			Log.v("kod", e.toString());
		}
		
		return sb.toString();
	}
}
