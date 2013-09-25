Dead simple TTS consisting of 1 tiny class 
------------------------------------------

Usage is as easy as 1-2-3:

1. Link your project with JLayer library for mp3 playing from java (I used jl1.0.1.jar).

2. Create instance: GoogleTextToSpeech gtts = new GoogleTextToSpeech().

3. Use: gtts.say("Hello everybody", "en"). First argument is phrase to say, second is language.