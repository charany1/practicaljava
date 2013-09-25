package com.example.TTS;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javazoom.jl.player.Player;

public class GoogleTextToSpeech {
	private static String ENCODING = "UTF-8";
	private static String URL_BEGINNING = "http://translate.google.com/translate_tts?ie=";
	private static String URL_QUERY = "&q=";
	private static String URL_TL = "&tl=";
	private static String USER_AGENT_LITERAL = "User-Agent";
	private static String USER_AGENT = "Mozilla/4.7";

	public void say( String phrase, String lang ) {
		
		try {
			//Make full URL
			phrase=URLEncoder.encode(phrase, ENCODING);
			String sURL = URL_BEGINNING + ENCODING + URL_TL + lang + URL_QUERY + phrase;
	        URL url = new URL(sURL);
	        
	        //Create connection
	        URLConnection urlConn = url.openConnection();
	        HttpURLConnection httpUrlConn = (HttpURLConnection) urlConn;
	        httpUrlConn.addRequestProperty(USER_AGENT_LITERAL, USER_AGENT);
	        
	        //Create stream
	        InputStream mp3WebStream = urlConn.getInputStream();
	        
	        //Play stream
	        Player plr = new Player(mp3WebStream);
	        plr.play();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
