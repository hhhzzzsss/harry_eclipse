package main;

import javax.sound.sampled.*;
import java.io.*;

public class Sound {
	
	public static final String PING = "Ping.wav";
	public static final String BING = "Bing.wav";
	
	public Clip loadClip(String filepath) {
		
		Clip clip = null;
		
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream( new File(filepath) );
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return clip;
		
	}
	
	public void playSound(String filepath) {
		if (Main.sound)
			loadClip(filepath).start();
	}
	
}
