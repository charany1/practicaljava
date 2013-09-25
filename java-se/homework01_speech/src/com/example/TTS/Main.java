package com.example.TTS;

import com.example.TTS.GoogleTextToSpeech;

public class Main {
	
	public static void main(String[] args) {
		GoogleTextToSpeech gtts = new GoogleTextToSpeech();
		gtts.say("Hello dear friends!", "en");
		gtts.say("Привет, друзья!", "ru");
	}

}
